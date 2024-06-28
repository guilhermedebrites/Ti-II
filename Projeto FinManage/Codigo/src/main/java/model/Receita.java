package model;

public class Receita {
    private int id;
	private String data;
	private double valor;
	private String fonte;
    private int id_usuario;
    
    public Receita(String data, double valor, String fonte, int id_usuario) {
        this.data = data;
        this.valor = valor;
        this.fonte = fonte;
        this.id_usuario = id_usuario;
    }
    public Receita(int id, String data, double valor, String fonte, int id_usuario) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.fonte = fonte;
        this.id_usuario = id_usuario;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getFonte() {
        return fonte;
    }
    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
