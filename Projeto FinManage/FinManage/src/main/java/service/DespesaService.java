package service;

import com.google.gson.Gson;

import dao.DespesaDAO;
import model.Despesa;
import spark.Request;
import spark.Response;

public class DespesaService {

	private DespesaDAO DespesaDAO = new DespesaDAO();

	public DespesaService() {

	}

	public String insert(Request request, Response response) {
		try{
			String json = request.body();
			Gson gson = new Gson();

			Despesa despesa = gson.fromJson(json, Despesa.class);
			
			if(DespesaDAO.insert(despesa) == true) {
				response.status(201); // 201 Created
				return "{\"message\": \"Despesa inserida com sucesso\"}";
			} else {
				response.status(404); // 404 Not found
				return "{\"message\": \"Despesa não foi inserida\"}";
			}
		}catch(NullPointerException e){
			response.status(500);
			return "{\"message\": \"Ocorreu um erro no servidor\"}";
		}
	}

	public Object get(Request request, Response response) {
		String json = request.body();
		Gson gson = new Gson();	

		int id = gson.fromJson(json, Despesa.class).getId();

		Despesa despesa = DespesaDAO.get(id);

		if (despesa != null) {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");

			return new Gson().toJson(despesa);
		} else {
			response.status(404); // 404 Not found
			return "{\"message\": \"Despesa não encontrada\"}";
		}
	}

	public Object update(Request request, Response response) {
		String json = request.body();
		Gson gson = new Gson();

		int id = gson.fromJson(json, Despesa.class).getId();

		Despesa despesaFromBody = gson.fromJson(json, Despesa.class);
		Despesa despesa = DespesaDAO.get(id);

		if (despesa != null) {
			despesa.setCategoria(despesaFromBody.getCategoria());
			despesa.setData(despesaFromBody.getData());
			despesa.setNome(despesaFromBody.getNome());
			despesa.setValor(despesaFromBody.getValor());
			DespesaDAO.update(despesa);
			response.status(200);
			return "{\"message\": \"Despesa atualizada com sucesso\"}";
		} else {
			response.status(404);
			return "{\"message\": \"Despesa não encontrada\"}";
		}
	}

	public Object delete(Request request, Response response) {
		String json = request.body();
		Gson gson = new Gson();

		int id = gson.fromJson(json, Despesa.class).getId();

		Despesa despesa = DespesaDAO.get(id);

		if (despesa != null) {
			DespesaDAO.delete(despesa.getId());
			response.status(200);
			return "{\"message\": \"Despesa excluída com sucesso\"}";
		} else {
			response.status(404);
			return "{\"message\": \"Despesa não encontrada\"}";
		}
	}

}