package application;

import java.util.Date;
import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entidades.Departamento;
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
		
		listaDeVendedores = vendedorDao.listarTodosOsVendedores();
		for(Vendedor vendedorPrint : listaDeVendedores) {
			System.out.println(vendedorPrint);
		}
		
		System.out.println();
		System.out.println("=============== Insert =======================================");
		
		vendedor = new Vendedor(null, "Joice", "joice.pimenta@gmail.com", new Date(), 5500.0, new Departamento(4, "Livros"));
		vendedorDao.insertVendedor(vendedor);
		System.out.println("Cadastrado no vendedor, ID: " + vendedor.getId());
		
		System.out.println();
		System.out.println("=============== Update =======================================");
		
		vendedor = vendedorDao.localizarVendedorPeloId(12);
		vendedor.setNome("Humberto Pacheco");
		vendedorDao.updateVendedor(vendedor);
		System.out.println(vendedor);
		
		System.out.println();
		System.out.println("=============== Delete =======================================");
		
		int idVendedor = 12;
		vendedorDao.deletarVendedorPeloId(idVendedor);
		System.out.println("Cadastro do vendedor ID: "+ idVendedor + " foi deletado com sucesso!");
	}

}
