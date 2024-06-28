package service;

import com.google.gson.Gson;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

import model.AnalistaFinanceiro;
import spark.Request;
import spark.Response;

public class IaService {

    private static final String API_KEY = "";

    public String chatGpt(Request request, Response response) {
    	
        try{
            String json = request.body();
            Gson gson = new Gson();
            AnalistaFinanceiro analista = gson.fromJson(json, AnalistaFinanceiro.class);

            OpenAiService service = new OpenAiService(API_KEY);

            String texto = "Você é um analista financeiro sênior, especializado em proporcionar orientações personalizadas para otimizar a saúde financeira." +
                "Suas principais qualidades incluem profundo conhecimento em estratégias de investimento, análise de riscos e soluções financeiras inovadoras." +
                "Preciso de orientações específicas com base em alguns parâmetros financeiros. Minha situação atual envolve:" +
                " Renda mensal: " + analista.getRendaMensal() +
                ", Investimentos: " + analista.getInvestimentos() +
                ", Dívidas: " + analista.getDividas() +
                ", Objetivo financeiro: " + analista.getObjetivoFinanceiro() +
                ". Retorne um JSON com os planos de ação no seguinte formato: {\"Investimento\": \"melhor estratégia para o caso\"," +
                "\"gestaoDeDividas\": [\"estratégia para fugir das dívidas\"], \"planejamentoFinanceiro\": [{\"Planejamento\": \"texto\"}]}. O planejamento será um array de objetos e deverá vir de acordo com o número de planejamentos [4]. Não me retorne nada mais que o JSON e Não altere o nome dos campos.";

            CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")                  
                .prompt(texto)
                .maxTokens(1000)
                .build();
            return service.createCompletion(completionRequest).getChoices().get(0).getText();
        }catch(Exception e){
            response.status(500);
            return "Erro ao processar requisição";
        }
        
    }
}
