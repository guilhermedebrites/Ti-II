package dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import model.Veiculo;

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
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO veiculo (codigo, placa, marca, motor) "
				       + "VALUES ("+veiculo.getCodigo()+ ", '" + veiculo.getPlaca() + "', '"  
				       + veiculo.getMarca() + "', '" + veiculo.getMotor() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Veiculo get(int codigo) {
		Veiculo veiculo = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 veiculo = new Veiculo(rs.getInt("codigo"), rs.getString("placa"), rs.getString("marca"), rs.getString("motor"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return veiculo;
	}
	
	
	public List<Veiculo> get() {
		return get("");
	}

	
	public List<Veiculo> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<Veiculo> getOrderByPlaca() {
		return get("placa");		
	}
	
	
	public List<Veiculo> getOrderByMarca() {
		return get("marca");		
	}
	
	
	private List<Veiculo> get(String orderBy) {	
	
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM veiculo" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Veiculo u = new Veiculo(rs.getInt("codigo"), rs.getString("placa"), rs.getString("marca"), rs.getString("motor"));
	        	veiculos.add(u);
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
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuario SET placa = '" + veiculo.getPlaca() + "', marca = '"  
				       + veiculo.getMarca() + "', motor = '" + veiculo.getMotor() + "'"
					   + " WHERE codigo = " + veiculo.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM veiculo WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}	
}