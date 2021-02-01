package application;

import java.util.Date;

import model.entidades.Departamento;
import model.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Departamento departamento = new Departamento(1, "Livros");
		Vendedor vendedor = new Vendedor(10, "João", "joao.pereira@gmail.com", new Date(), 1000.0, departamento);
		System.out.println(vendedor);

	}

}
