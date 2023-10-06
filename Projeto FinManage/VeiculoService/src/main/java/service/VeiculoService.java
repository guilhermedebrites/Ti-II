package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.VeiculoDAO;
import model.Veiculo;
import spark.Request;
import spark.Response;


public class VeiculoService {

	private VeiculoDAO VeiculoDAO = new VeiculoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	
	public VeiculoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Veiculo(), FORM_ORDERBY_DESCRICAO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Veiculo(), orderBy);
	}

	
	public void makeForm(int tipo, Veiculo veiculo, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umVeiculo = "";
		if(tipo != FORM_INSERT) {
			umVeiculo += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/produto/list/1\">Novo Produto</a></b></font></td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t</table>";
			umVeiculo += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/produto/";
			String name, placa, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Veiculo";
				placa = "carro, moto, aviao";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + veiculo.getID();
				name = "Atualizar Veiculo (ID " + veiculo.getID() + ")";
				placa = veiculo.getPlaca();
				buttonLabel = "Atualizar";
			}
			umVeiculo += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umVeiculo += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td>&nbsp;Placa: <input class=\"input--register\" type=\"text\" name=\"placa\" value=\""+ placa +"\"></td>";
			umVeiculo += "\t\t\t<td>Preco: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ veiculo.getPreco() +"\"></td>";
			umVeiculo += "\t\t\t<td>Quantidade: <input class=\"input--register\" type=\"text\" name=\"quantidade\" value=\""+ veiculo.getQuantidade() +"\"></td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t</table>";
			umVeiculo += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umVeiculo += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Veiculo (ID " + veiculo.getID() + ")</b></font></td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td>&nbsp;Placa: "+ veiculo.getPlaca() +"</td>";
			umVeiculo += "\t\t\t<td>Preco: "+ veiculo.getPreco() +"</td>";
			umVeiculo += "\t\t\t<td>Quantidade: "+ veiculo.getQuantidade() +"</td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t\t<tr>";
			umVeiculo += "\t\t\t<td>&nbsp;</td>";
			umVeiculo += "\t\t</tr>";
			umVeiculo += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-VEICULO>", umVeiculo);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Veiculos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_DESCRICAO + "\"><b>Placa</b></a></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Veiculo> veiculos;
		if (orderBy == FORM_ORDERBY_ID) {                 	veiculos = VeiculoDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_DESCRICAO) {		veiculos = VeiculoDAO.getOrderByPlaca();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			veiculos = VeiculoDAO.getOrderByPreco();
		} else {											veiculos = VeiculoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Veiculo p : veiculos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getPlaca() + "</td>\n" +
            		  "\t<td>" + p.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + p.getID() + "', '" + p.getPlaca() + "', '" + p.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-PRODUTO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String placa = request.queryParams("placa");
		double preco = Double.parseDouble(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		
		String resp = "";
		
		Veiculo veiculo = new Veiculo(-1, placa, preco, quantidade);
		
		if(VeiculoDAO.insert(veiculo) == true) {
            resp = "Veiculo (" + placa + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Veiculo (" + placa + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Veiculo veiculo = (Veiculo) VeiculoDAO.get(id);
		
		if (veiculo != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, veiculo, FORM_ORDERBY_DESCRICAO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Veiculo " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Veiculo veiculo = (Veiculo) VeiculoDAO.get(id);
		
		if (veiculo != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, veiculo, FORM_ORDERBY_DESCRICAO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Veiculo " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Veiculo veiculo = VeiculoDAO.get(id);
        String resp = "";       

        if (veiculo != null) {
        	veiculo.setPlaca(request.queryParams("placa"));
        	veiculo.setPreco(Float.parseFloat(request.queryParams("preco")));
        	veiculo.setQuantidade(Integer.parseInt(request.queryParams("quantidade")));
        	VeiculoDAO.update(veiculo);
        	response.status(200); // success
            resp = "Veiculo (ID " + veiculo.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Veiculo (ID \" + veiculo.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Veiculo veiculo = VeiculoDAO.get(id);
        String resp = "";       

        if (veiculo != null) {
            VeiculoDAO.delete(id);
            response.status(200); // success
            resp = "Veiculo (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Veiculo (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}