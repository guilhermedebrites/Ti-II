package service;

import dao.DespesaDAO;
import model.Despesa;
import spark.Request;
import spark.Response;


public class DespesaService {

	private DespesaDAO DespesaDAO = new DespesaDAO();
	
	
	public DespesaService() {
		
	}
	
	
	public String insert(Request request, Response response) {
		String categoria = request.queryParams("categoria");
		String data = request.queryParams("data");
		double valor = Double.parseDouble(request.queryParams("valor"));
		String nome = request.queryParams("nome");
		int idUsuario = Integer.parseInt(request.queryParams("id_usuario"));

		Despesa despesa = new Despesa(categoria, data, valor, nome, idUsuario);
		
		if(DespesaDAO.insert(despesa) == true) {
            response.status(201); // 201 Created
			return "{\"message\": \"Despesa inserida com sucesso\"}";
		} else {
			response.status(404); // 404 Not found
			return "{\"message\": \"Despesa não foi inserida\"}";
		}
	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.queryParams("id"));
		Despesa despesa = DespesaDAO.get(id);
		
		if(despesa != null){
			despesa.setCategoria(request.queryParams("categoria"));
			despesa.setData(request.queryParams("data"));
			despesa.setNome(request.queryParams("nome"));
			despesa.setValor(Double.parseDouble(request.queryParams("valor")));
			DespesaDAO.update(despesa);
			response.status(200);
			return "{\"message\": \"Despesa atualizada com sucesso\"}";
		}else{
			response.status(404);
			return "{\"message\": \"Despesa não encontrada\"}";
		}

	}

}