package model;

public class AnalistaFinanceiro {

    private int idade;
    private double rendaMensal;
    private double investimentos;
    private double dividas;
    private String objetivoFinanceiro;

    public AnalistaFinanceiro(int idade, double rendaMensal, double patrimonio, double investimentos, double dividas, String objetivoFinanceiro) {
        this.idade = idade;
        this.rendaMensal = rendaMensal;
        this.investimentos = investimentos;
        this.dividas = dividas;
        this.objetivoFinanceiro = objetivoFinanceiro;
    }

    public AnalistaFinanceiro() {
        
    }

    public int getIdade() {
        return idade;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public double getInvestimentos() {
        return investimentos;
    }

    public double getDividas() {
        return dividas;
    }

    public String getObjetivoFinanceiro() {
        return objetivoFinanceiro;
    }
}

