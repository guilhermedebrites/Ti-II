package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DespesaFixa;

public class DespesaFixaDAO extends DAO {
    
    public DespesaFixaDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(DespesaFixa despesaFixa) {
        boolean status = false;
        try {
            String sql = "INSERT INTO despesas_fixas (nome, valor, id_usuario, recorrencia) "
                    + "VALUES ('" + despesaFixa.getNome() + "', '"
                    + despesaFixa.getValor() + "', '" + despesaFixa.getId_usuario() + "', '"
                    + despesaFixa.getRecorrencia() + "');";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public ArrayList<DespesaFixa> get(int id) {
        ArrayList<DespesaFixa> lista = new ArrayList<DespesaFixa>();
		DespesaFixa despesa = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM despesas_fixas WHERE id_usuario =" + id + ";";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				despesa = new DespesaFixa(rs.getInt("id"),rs.getString("nome"), rs.getDouble("valor"),
                        rs.getInt("id_usuario"), rs.getString("recorrencia"));
				lista.add(despesa);
			}

			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return lista;
    }

    public DespesaFixa getAll(int id) {
        DespesaFixa despesaFixa = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM despesas_fixas WHERE id = '" + id + "';";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                despesaFixa = new DespesaFixa(rs.getInt("id"),rs.getString("nome"), rs.getDouble("valor"),
                        rs.getInt("id_usuario"), rs.getString("recorrencia"));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return despesaFixa;
    }

    public boolean update(DespesaFixa despesa, int id){
        boolean status = false;
        try{
            String sql = "UPDATE despesas_fixas SET nome = '" + despesa.getNome() + "', valor = '" + despesa.getValor() + "', id_usuario = '" + despesa.getId_usuario() + "', recorrencia = '" + despesa.getRecorrencia() + "' WHERE id = '" + id + "';";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int id){
        boolean status = false;
        try{
            String sql = "DELETE FROM despesas_fixas WHERE id = '" + id + "';";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return status;
    }

}
