package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Despesa;

public class DespesaDAO extends DAO {
	public DespesaDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Despesa despesa) {
		boolean status = false;
		try {
			String sql = "INSERT INTO despesa (categoria, data, valor, nome, id_usuario) "
					+ "VALUES ('" + despesa.getCategoria() + "', '"
					+ despesa.getData() + "', '" + despesa.getValor() + "', '"
					+ despesa.getNome() + "', '" + despesa.getId_usuario() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Despesa getAll(int id) {
		Despesa despesa = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM despesa WHERE id =" + id + ";";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				despesa = new Despesa(rs.getInt("id"),rs.getString("categoria"), rs.getString("data"),
						rs.getDouble("valor"), rs.getString("nome"), rs.getInt("id_usuario"));
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return despesa;
	}

	public ArrayList<Despesa> get(int id) {
		ArrayList<Despesa> lista = new ArrayList<Despesa>();
		Despesa despesa = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM despesa WHERE id_usuario =" + id + ";";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				despesa = new Despesa(rs.getInt("id"), rs.getString("categoria"), rs.getString("data"), rs.getDouble("valor"), rs.getString("nome"), rs.getInt("id_usuario"));
				lista.add(despesa);
			}

			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return lista;
	}

	public boolean update(Despesa despesa, int id) {
		boolean status = false;
		try {
			String sql = "UPDATE despesa SET categoria = '" + despesa.getCategoria() + "', " +
					"data = '" + despesa.getData() + "', " +
					"valor = " + despesa.getValor() + ", " +
					"nome = '" + despesa.getNome() + "' " +
					" WHERE id = '" + id + "';";

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
			String sql = "DELETE FROM despesa WHERE id = " + id;
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

}
