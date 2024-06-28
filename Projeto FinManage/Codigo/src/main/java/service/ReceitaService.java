package service;

import java.util.ArrayList;

import com.google.gson.Gson;

import dao.ReceitaDAO;
import model.Receita;
import spark.Request;
import spark.Response;

public class ReceitaService {
    private ReceitaDAO ReceitaDAO = new ReceitaDAO();

	public ReceitaService() {

	}

    public String insert(Request request, Response response) {
        try{
            String json = request.body();
            Gson gson = new Gson();

            Receita receita = gson.fromJson(json, Receita.class);
            
            if(ReceitaDAO.insert(receita) == true) {
                response.status(201); // 201 Created
                return "{\"message\": \"Receita inserida com sucesso\"}";
            } else {
                response.status(404); // 404 Not found
                return "{\"message\": \"Receita não foi inserida\"}";
            }
        }catch(Exception e){
            response.status(500); // Internal server error
            return "{\"message\": \"Ocorreu um erro no servidor\"}";
        }
    }

    public String get(Request request, Response response) {
        try{
            int id = Integer.parseInt(request.params(":id"));
            ArrayList<Receita> receita = ReceitaDAO.get(id);

            if(receita != null) {
                response.status(200); 
                response.header("Content-Type", "application/json");
			    response.header("Content-Encoding", "UTF-8");
                return new Gson().toJson(receita);
            } else {
                response.status(404); // 404 Not found
                return "{\"message\": \"Receita não encontrada\"}";
            }
        }catch(Exception e){
            response.status(500); // Internal server error
            return "{\"message\": \"Ocorreu um erro no servidor\"}";
        }
    }

    public String update(Request request, Response response) {
        try{
            int id = Integer.parseInt(request.params(":id"));
            String json = request.body();
            Gson gson = new Gson();

            Receita receita = gson.fromJson(json, Receita.class);
            
            if(ReceitaDAO.update(id, receita) == true) {
                response.status(201); // 201 Created
                return "{\"message\": \"Receita atualizada com sucesso\"}";
            } else {
                response.status(404); // 404 Not found
                return "{\"message\": \"Receita não foi atualizada\"}";
            }
        }catch(Exception e){
            response.status(500); // Internal server error
            return "{\"message\": \"Ocorreu um erro no servidor\"}";
        }
    }

    public String delete(Request request, Response response) {
        try{
            int id = Integer.parseInt(request.params(":id"));
            
            if(ReceitaDAO.delete(id) == true) {
                response.status(201); // 201 Created
                return "{\"message\": \"Receita deletada com sucesso\"}";
            } else {
                response.status(404); // 404 Not found
                return "{\"message\": \"Receita não foi deletada\"}";
            }
        }catch(Exception e){
            response.status(500); // Internal server error
            return "{\"message\": \"Ocorreu um erro no servidor\"}";
        }
    }
}
