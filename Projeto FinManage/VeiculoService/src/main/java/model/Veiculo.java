package model;

public class Veiculo {
	private int id;
	private String placa;
	private double preco;
	private int quantidade;
	
	public Veiculo() {
		id = -1;
		placa = "";
		preco = 0.0;
		quantidade = 0;
	}

	public Veiculo(int id, String placa, double preco, int quantidade) {
		setId(id);
		setPlaca(placa);
		setPreco(preco);
		setQuantidade(quantidade);
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Veiculo: " + placa + "   Preço: R$" + preco + "   Quantidade.: " + quantidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Veiculo) obj).getID());
	}	
}