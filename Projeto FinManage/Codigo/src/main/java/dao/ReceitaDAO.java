package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Receita;

public class ReceitaDAO extends DAO {
    public ReceitaDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Receita receita) {
        boolean status = false;
        try {
            String sql = "INSERT INTO receita (data, valor, fonte, id_usuario)"
                    + "VALUES ('" + receita.getData() + "', '" + receita.getValor() + "', '"
                    + receita.getFonte() + "', '" + receita.getId_usuario() + "');";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public ArrayList<Receita> get(int id) {
        ArrayList<Receita> lista = new ArrayList<Receita>();
        Receita receita = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM receita WHERE id_usuario =" + id + ";";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                receita = new Receita(rs.getInt("id"), rs.getString("data"), rs.getDouble("valor"),
                        rs.getString("fonte"), rs.getInt("id_usuario"));
                lista.add(receita);
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return lista;
    }

    public boolean update(int id,Receita receita) {
        boolean status = false;
        try {
            String sql = "UPDATE receita SET data = '" + receita.getData() + "', valor = '" + receita.getValor()
                    + "', fonte = '" + receita.getFonte() + "', id_usuario = '" + receita.getId_usuario()
                    + "' WHERE id = " + id + ";";
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
            String sql = "DELETE FROM receita WHERE id = " + id + ";";
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
