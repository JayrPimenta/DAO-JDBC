package model.dao;

import java.util.List;

import model.entidades.Vendedor;

public interface VendedorDao {

	void insertVendedor(Vendedor vendedor);
	void updateVendedor(Vendedor vendedor);
	void deletarVendedorPeloId(Integer id);
	Vendedor localizarVendedorPeloId(Integer id);
	List<Vendedor> listarTodosOsVendedores();
	List<Vendedor> listarPorDepartamento(Integer departamentoId);
}
