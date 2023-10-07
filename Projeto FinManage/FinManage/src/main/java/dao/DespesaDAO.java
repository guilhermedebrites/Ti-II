package dao;

public class DespesaDAO extends DAO{
    public DespesaDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
}
