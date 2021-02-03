package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DepartamentoDao;
import model.dao.FabricaDao;
import model.entidades.Departamento;

public class ProgramaDepartamento {

	public static void main(String[] args) {
		
		List<Departamento> listaDeDepartamentos = new ArrayList<>();
		DepartamentoDao departamentoDao = FabricaDao.criarDepartamentoDao();
		
		System.out.println();
		System.out.println("=============== Localizar Pelo do Departamento ===============");
		
		Departamento departamento = departamentoDao.localizarDepartamentoPeloId(2);
		System.out.println(departamento);
		
		
		System.out.println();
		System.out.println("=============== Imprimir todos os Departamentos ==============");
		listaDeDepartamentos = departamentoDao.listarTodosOsDepartamentos();
		for(Departamento departamentoItem : listaDeDepartamentos) {
			System.out.println(departamentoItem);
		}
		
		System.out.println();
		System.out.println("=============== Insert =======================================");
		departamento = new Departamento(null, "Nada");
		departamentoDao.insertDepartamento(departamento);
		System.out.println("Adicionado novo departamento ID: "+ departamento.getId() + " Nome: "+ departamento.getNome());
		
		System.out.println();
		System.out.println("=============== Update =======================================");
		departamento = new Departamento(7, "Comida");
		departamentoDao.updateDepartamento(departamento);;
		System.out.println("Adicionado novo departamento ID: "+ departamento.getId() + " Nome: "+ departamento.getNome());
		
		System.out.println();
		System.out.println("=============== Delete =======================================");
		int codigoDepartamento = 8;
		departamentoDao.deletarDepartamentoPeloId(codigoDepartamento);
		System.out.println("Departamento codigo: "+ codigoDepartamento + " foi deletado com sucesso!");
		
		
	}

}
