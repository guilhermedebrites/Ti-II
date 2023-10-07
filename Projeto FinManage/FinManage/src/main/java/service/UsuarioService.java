package service;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {

	private UsuarioDAO UsuarioDAO = new UsuarioDAO();
	
	
	public UsuarioService() {
		
	}
	
	
	public String insert(Request request, Response response) {
		String nome = request.queryParams("nome_completo");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");

		Usuario usuario = new Usuario(nome, email, senha);
		
		if(UsuarioDAO.insert(usuario) == true) {
            response.status(201); // 201 Created
			return "{\"message\": \"Usuário inserido com sucesso\"}";
		} else {
			response.status(404); // 404 Not found
			return "{\"message\": \"Usuário não foi inserido\"}";
		}
	}
}