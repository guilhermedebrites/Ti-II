package model;

public class Despesa {
    private int id;
    private String categoria;
    private String data;
    private double valor;
    private String nome;
    private int id_usuario;

    public Despesa(String categoria, String data, double valor, String nome, int id_usuario) {
        this.categoria = categoria;
        this.data = data;
        this.valor = valor;
        this.nome = nome;
        this.id_usuario = id_usuario;
    }

     public Despesa(int id,String categoria, String data, double valor, String nome, int id_usuario) {
        this.id = id;
        this.categoria = categoria;
        this.data = data;
        this.valor = valor;
        this.nome = nome;
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
