package dao;

import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO extends DAO {	
	public VeiculoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Veiculo veiculo) {
		boolean status = false;
		try {
			String sql = "INSERT INTO veiculo (placa, preco, quantidade) "
		               + "VALUES ('" + veiculo.getPlaca() + "', "
		               + veiculo.getPreco() + ", " + veiculo.getQuantidade() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Veiculo get(int id) {
		Veiculo produto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM veiculo WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 produto = new Veiculo(rs.getInt("id"), rs.getString("placa"),
	                				   (float)rs.getDouble("preco"), rs.getInt("quantidade"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produto;
	}
	
	
	public List<Veiculo> get() {
		return get("");
	}

	
	public List<Veiculo> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Veiculo> getOrderByPlaca() {
		return get("placa");		
	}
	
	
	public List<Veiculo> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Veiculo> get(String orderBy) {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM veiculo" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Veiculo p = new Veiculo(rs.getInt("id"), rs.getString("placa"),
     				   					(float)rs.getDouble("preco"), rs.getInt("quantidade"));
	            veiculos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return veiculos;
	}
	
	
	public boolean update(Veiculo veiculo) {
		boolean status = false;
		try {  
			String sql = "UPDATE veiculo " +
		              "SET placa = '" + veiculo.getPlaca() + "', " +
		              "preco = " + veiculo.getPreco() + ", " +
		              "quantidade = " + veiculo.getQuantidade() + " " +
		              "WHERE id = " + veiculo.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM veiculo WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}