package service;

import java.util.ArrayList;

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
		String id = request.params(":id");

		ArrayList<Despesa> despesas = DespesaDAO.get(Integer.parseInt(id));

		if (despesas != null) {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");

			return new Gson().toJson(despesas);
		} else {
			response.status(404); // 404 Not found
			return "{\"message\": \"Despesa não encontrada\"}";
		}
	}

	public Object update(Request request, Response response) {
		try{
			String json = request.body();
			Gson gson = new Gson();
			String id = request.params(":id");
	
			Despesa despesa = gson.fromJson(json, Despesa.class);
	
			if (DespesaDAO.update(despesa, Integer.parseInt(id)) == true) {
				response.status(200);
				return new Gson().toJson(despesa);
			} else {
				response.status(404);
				return "{\"message\": \"Despesa não encontrada\"}";
			}
		}catch(NullPointerException e){
			response.status(500);
			return "{\"message\": \"Ocorreu um erro no servidor\"}";
		}
	}

	public Object delete(Request request, Response response) {
		String id = request.params(":id");

		Despesa despesa = DespesaDAO.getAll(Integer.parseInt(id));

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