package application;

import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		System.out.println("=============== Localizar Pelo ID do Vendedor ================");
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		Vendedor vendedor = vendedorDao.localizarVendedorPeloId(3);
		System.out.println(vendedor);
		
		System.out.println();
		System.out.println("=============== Localizar Pelo do Departamento ===============");
		
		List<Vendedor> listaDeVendedores = vendedorDao.listarPorDepartamento(2);
		for(Vendedor vendedorPrint : listaDeVendedores) {
			System.out.println(vendedorPrint);
		}
		
		System.out.println();
		System.out.println("=============== Imprimir Todos ===============================");
		
		List<Vendedor> listaDeVendedoresCompleta = vendedorDao.listarTodosOsVendedores();
		for(Vendedor vendedorPrint : listaDeVendedoresCompleta) {
			System.out.println(vendedorPrint);
		}
		

	}

}
