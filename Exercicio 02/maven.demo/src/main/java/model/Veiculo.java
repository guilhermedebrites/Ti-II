package model;

public class Veiculo {
	private int codigo;
	private String placa;
	private String marca;
	private String motor;
	
	public Veiculo() {
		this.codigo = -1;
		this.placa = "";
		this.marca = "";
		this.motor = "";
	}
	
	public Veiculo(int codigo, String placa, String marca, String motor) {
		this.codigo = codigo;
		this.placa = placa;
		this.marca = marca;
		this.motor = motor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	@Override
	public String toString() {
		return "Veiculo [codigo=" + codigo + ", placa=" + placa + ", marca=" + marca + ", motor=" + motor + "]";
	}	
}