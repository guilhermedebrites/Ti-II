package model;

import java.math.BigInteger;
import java.security.MessageDigest;

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

	public Usuario(int id2, String nomeCompleto2, String email2) {
		this.id = id2;
		this.nomeCompleto = nomeCompleto2;
		this.email = email2;
	}

	public BigInteger CriptografarSenha(String senha) {
		MessageDigest md5;
		BigInteger senhacrip = null;

		try{
			md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(senha.getBytes(), 0, senha.length());
			senhacrip = new BigInteger(1, md5.digest());
		}catch(Exception e){
			e.printStackTrace();
		}

		return senhacrip;
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