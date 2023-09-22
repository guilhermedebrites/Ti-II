package app;

import static spark.Spark.*;
import service.VeiculoService;


public class Aplicacao {
	
	private static VeiculoService veiculoService = new VeiculoService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/produto/insert", (request, response) -> veiculoService.insert(request, response));

        get("/produto/:id", (request, response) -> veiculoService.get(request, response));
        
        get("/produto/list/:orderby", (request, response) -> veiculoService.getAll(request, response));

        get("/produto/update/:id", (request, response) -> veiculoService.getToUpdate(request, response));
        
        post("/produto/update/:id", (request, response) -> veiculoService.update(request, response));
           
        get("/produto/delete/:id", (request, response) -> veiculoService.delete(request, response));

             
    }
}