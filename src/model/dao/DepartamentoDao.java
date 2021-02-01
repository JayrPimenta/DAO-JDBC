package model.dao;

import java.util.List;

import model.entidades.Departamento;

public interface DepartamentoDao {
	
	void insertDepartamento(Departamento departamento);
	void updateDepartamento(Departamento departamento);
	void deletarDepartamentoPeloId(Integer id);
	Departamento localizarDepartamentoPeloId(Integer id);
	List<Departamento> listarTodosOsDepartamentos();

}
