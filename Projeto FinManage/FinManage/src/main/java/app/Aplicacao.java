package app;

import static spark.Spark.*;

import service.DespesaService;
import service.UsuarioService;


public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
    private static DespesaService despesaService = new DespesaService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));
        
        post("/despesa/insert", (request, response) -> despesaService.insert(request, response));

        post("/despesa/update", (request, response) -> despesaService.update(request, response));        
    }
}