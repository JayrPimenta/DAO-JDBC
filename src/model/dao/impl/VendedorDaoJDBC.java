package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao{
	
	private Connection conexao;
	
	public VendedorDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insertVendedor(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVendedor(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarVendedorPeloId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor localizarVendedorPeloId(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			preparedStatement = conexao.prepareStatement("SELECT vendedores.*,departamento.nome as DepNome From vendedores INNER JOIN departamento ON vendedores.DepartamentoId = departamento.Id WHERE vendedores.Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Departamento departamento = iniciarDepartamento(resultSet);
				Vendedor vendedor = iniciarVendedor(resultSet, departamento);
				return vendedor;
				
			}
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(preparedStatement);
			DB.fecharResultSet(resultSet);
		}
	}

	// Corrigir depois apartir da aula 250
	@Override
	public List<Vendedor> listarTodosOsVendedores() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			preparedStatement = conexao.prepareStatement("SELECT vendedores.*,departamento.nome as DepNome From vendedores INNER JOIN departamento ON vendedores.DepartamentoId = departamento.Id");
			resultSet = preparedStatement.executeQuery();
			
			Map<Integer, Departamento> map = new HashMap<>();
			List<Vendedor> vendedores = new ArrayList<>();

			while(resultSet.next()) {
				
				Departamento departamento = map.get(resultSet.getInt("DepartamentoId"));
				
				if(departamento == null) {
					departamento = iniciarDepartamento(resultSet);
					map.put(resultSet.getInt("DepartamentoId"), departamento);
				}
				vendedores.add(iniciarVendedor(resultSet, departamento));
				
			}
			return vendedores;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(preparedStatement);
			DB.fecharResultSet(resultSet);
		}
	}
	
	@Override
	public List<Vendedor> listarPorDepartamento(Integer departamentoId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Vendedor> listaDeVendedores = new ArrayList<>();
		try {
			
			preparedStatement = conexao.prepareStatement("SELECT vendedores.*,departamento.nome as DepNome From vendedores INNER JOIN departamento ON vendedores.DepartamentoId = departamento.Id WHERE DepartamentoId = ? ORDER BY Nome");
			preparedStatement.setInt(1, departamentoId);
			resultSet = preparedStatement.executeQuery();
			Departamento departamento = null;
			
			while(resultSet.next()) {
				
				if (departamento == null) {
					departamento = iniciarDepartamento(resultSet);
				}
				
				listaDeVendedores.add(iniciarVendedor(resultSet, departamento));	
			}
			return listaDeVendedores;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(preparedStatement);
			DB.fecharResultSet(resultSet);
		}
	}
	
	
	private Departamento iniciarDepartamento(ResultSet resultSet) throws SQLException {
		Departamento departamento = new Departamento(resultSet.getInt("DepartamentoId"), resultSet.getString("DepNome"));
		return departamento;
	}
	
	private Vendedor iniciarVendedor(ResultSet resultSet, Departamento departamento) throws SQLException {
		Vendedor vendedor = new Vendedor(resultSet.getInt("Id"), 
				resultSet.getString("Nome"),
				resultSet.getString("Email"),
				resultSet.getDate("DataNascimento"),
				resultSet.getDouble("SalarioBase"), departamento);
		return vendedor;
	}




	

}
