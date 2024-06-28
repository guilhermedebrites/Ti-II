package app;

import static spark.Spark.*;
import service.DespesaService;
import service.IaService;
import service.ReceitaService;
import service.UsuarioService;
import service.DespesaFixaService;

import java.util.HashMap;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;


public class Aplicacao {

    private static UsuarioService usuarioService = new UsuarioService();
    private static DespesaService despesaService = new DespesaService();
    private static DespesaFixaService despesaFixService = new DespesaFixaService();
    private static ReceitaService receitaService = new ReceitaService();
    private static IaService iaService = new IaService();

    public static void main(String[] args) {
        port(6789);

        staticFiles.location("/public");

        CorsFilter.apply();

        // Options handler for CORS
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
        // before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        //USUARIOS
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));

        get("/usuario/get", (request, response) -> usuarioService.get(request, response));

        get("/usuario/getAll", (request, response) -> usuarioService.getAll(request, response));

        post("/usuario/authenticate", (request, response) -> usuarioService.authenticate(request, response));

        //DESPESA
        post("/despesa/insert", (request, response) -> despesaService.insert(request, response));

        get("/despesa/get/:id", (request, response) -> despesaService.get(request, response));

        put("/despesa/update/:id", (request, response) -> despesaService.update(request, response));

        delete("/despesa/delete/:id", (request, response) -> despesaService.delete(request, response));

        //DESPESA FIXA
        post("/despesa-fix/insert", (request, response) -> despesaFixService.insert(request, response));

        get("/despesa-fix/get/:id", (request, response) -> despesaFixService.get(request, response));

        put("/despesa-fix/update/:id", (request, response) -> despesaFixService.update(request, response));

        delete("/despesa-fix/delete/:id", (request, response) -> despesaFixService.delete(request, response));

        //RECEITA

        post("/receita/insert", (request, response) -> receitaService.insert(request, response));

        get("/receita/get/:id", (request, response) -> receitaService.get(request, response));

        put("/receita/update/:id", (request, response) -> receitaService.update(request, response));

        delete("/receita/delete/:id", (request, response) -> receitaService.delete(request, response));

        //IA

        post("/ia", (request, response) -> iaService.chatGpt(request, response));

    }
}

class CorsFilter {

    private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();

    static {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        corsHeaders.put("Access-Control-Allow-Origin", "*");
        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, Content-Length, Accept, Origin");
        corsHeaders.put("Access-Control-Allow-Credentials", "true");
        corsHeaders.put("Access-Control-Expose-Headers", "Content-Length, Access-Control-Allow-Origin, Access-Control-Allow-Headers");
    }

    public final static void apply() {
        Filter filter = new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                corsHeaders.forEach((key, value) -> {
                    response.header(key, value);
                });
            }
        };
        Spark.after(filter);
    }
}
