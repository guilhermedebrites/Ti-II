package service;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;


public class UsuarioService {

	private UsuarioDAO UsuarioDAO = new UsuarioDAO();
	
	
	public UsuarioService() {
		
	}
	
	
	public String insert(Request request, Response response) {
		
		String json = request.body();
		Gson gson = new Gson();
		
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		if(UsuarioDAO.insert(usuario) == true) {
            response.status(201); // 201 Created
			return "{\"message\": \"Usuário inserido com sucesso\"}";
		} else {
			response.status(404); // 404 Not found
			return "{\"message\": \"Usuário não foi inserido\"}";
		}
	}

	public String get(Request request, Response response) {

		String json = request.body();
		Gson gson = new Gson();

		int id = gson.fromJson(json, Usuario.class).getId();
		Usuario usuario = UsuarioDAO.get(id);
		
		if (usuario != null) {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return new Gson().toJson(usuario);
		} else {
			response.status(404); // 404 Not found
			return "{\"message\": \"Usuário não encontrado\"}";
		}
	}
}