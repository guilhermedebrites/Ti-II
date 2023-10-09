package dao;

import model.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO extends DAO {
	public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Usuario user) {
		boolean status = false;
		try {
			String sql = "INSERT INTO usuarios (nome_completo, email, senha) "
					+ "VALUES ('" + user.getNomeCompleto() + "', '"
					+ user.getEmail() + "', '" + user.getSenha() + "');";
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