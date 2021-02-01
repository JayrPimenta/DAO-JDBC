package application;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		Vendedor vendedor = vendedorDao.localizarVendedorPeloId(3);
		System.out.println(vendedor);

	}

}
