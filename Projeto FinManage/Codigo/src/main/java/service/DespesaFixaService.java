package service;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.DespesaFixaDAO;
import model.DespesaFixa;
import spark.Request;
import spark.Response;

public class DespesaFixaService {
    
    private DespesaFixaDAO DespesaFixaDAO = new DespesaFixaDAO();

    public DespesaFixaService() {

    }

    public String insert(Request request, Response response) {
        try{
            String json = request.body();
            Gson gson = new Gson();

            DespesaFixa despesaFixa = gson.fromJson(json, DespesaFixa.class);
            
            if(DespesaFixaDAO.insert(despesaFixa) == true) {
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

    public String get(Request request, Response response){

        String id = request.params(":id");

		ArrayList<DespesaFixa> despesas = DespesaFixaDAO.get(Integer.parseInt(id));

		if (despesas != null) {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");

			return new Gson().toJson(despesas);
		} else {
			response.status(404); // 404 Not found
			return "{\"message\": \"Despesa não encontrada\"}";
		}
    }

    public String update(Request request, Response response){
        try{
            String json = request.body();
            Gson gson = new Gson();
            String id = request.params(":id");

            DespesaFixa despesaFixa = gson.fromJson(json, DespesaFixa.class);

            if(DespesaFixaDAO.update(despesaFixa, Integer.parseInt(id)) == true){
                response.status(200);
                return new Gson().toJson(despesaFixa);
            }else{
                response.status(404);
                return "{\"message\": \"Despesa não encontrada\"}";
            }

        }catch(NullPointerException e){
            response.status(500);
            return "{\"message\": \"Ocorreu um erro no servidor\"}";
        }
    }

    public String delete(Request request, Response response){
        try{
            String id = request.params(":id");

            if(DespesaFixaDAO.delete(Integer.parseInt(id)) == true){
                response.status(200);
                return "{\"message\": \"Despesa deletada com sucesso\"}";
            }else{
                response.status(404);
                return "{\"message\": \"Despesa não encontrada\"}";
            }
        }catch(NullPointerException e){
            response.status(500);
            return "{\"message\": \"Ocorreu um erro no servidor\"}";
        }
    }
}