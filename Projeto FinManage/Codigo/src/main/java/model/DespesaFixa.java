package model;

public class DespesaFixa {
    private int id;
    private String nome;
    private double valor;
    private int id_usuario;
    private String recorrencia;

    public DespesaFixa() {
        
    }

    public DespesaFixa(String nome, double valor, int id_usuario, String recorrencia) {
        this.nome = nome;
        this.valor = valor;
        this.id_usuario = id_usuario;
        this.recorrencia = recorrencia;
    }

    public DespesaFixa(int id, String nome, double valor, int id_usuario, String recorrencia) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.id_usuario = id_usuario;
        this.recorrencia = recorrencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(String recorrencia) {
        this.recorrencia = recorrencia;
    }

}
