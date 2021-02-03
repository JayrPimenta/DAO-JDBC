package model.dao;
	
import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

public class FabricaDao {

	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJDBC(DB.iniciarConexao());
	}
	
	public static DepartamentoDao criarDepartamentoDao() {
		return new DepartamentoDaoJDBC(DB.iniciarConexao());
	}
	
}
