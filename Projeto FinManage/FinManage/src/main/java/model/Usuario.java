package model;

public class Usuario {
	private int id;
	private String nomeCompleto;
	private String email;
	private String senha;

	public Usuario(String nomeCompleto, String email, String senha) {
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
	}

	public Usuario(int id,String nomeCompleto, String email, String senha) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}