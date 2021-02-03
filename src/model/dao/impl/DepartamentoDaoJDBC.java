package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entidades.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao{

	private Connection conexao;
	
	public DepartamentoDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public void insertDepartamento(Departamento departamento) {
		PreparedStatement preparedStatement = null;
		try {
			
			preparedStatement = conexao.prepareStatement("INSERT INTO departamento SET Nome = ?", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, departamento.getNome());
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					departamento.setId(resultSet.getInt(1));
				}
				DB.fecharResultSet(resultSet);
			} else {
				throw new DbException("Erro inesperado: Nenhuma linha afetada");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(preparedStatement);
		}
	}

	@Override
	public void updateDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarDepartamentoPeloId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departamento localizarDepartamentoPeloId(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			preparedStatement = conexao.prepareStatement("SELECT * FROM departamento WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			Departamento departamento = null;

			if(resultSet.next()) {
				departamento = iniciarDepartamento(resultSet);
			}
			
			return departamento;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(preparedStatement);
			DB.fecharResultSet(resultSet);
		}
	}

	@Override
	public List<Departamento> listarTodosOsDepartamentos() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			preparedStatement = conexao.prepareStatement("SELECT * FROM departamento");
			resultSet = preparedStatement.executeQuery();
			
			List<Departamento> listaDeDepartamentos = new ArrayList<>();
			
			while(resultSet.next()) {
				listaDeDepartamentos.add(iniciarDepartamento(resultSet));
			}
			
			return listaDeDepartamentos;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(preparedStatement);
			DB.fecharResultSet(resultSet);
		}
	}
	
	private Departamento iniciarDepartamento(ResultSet resultSet) throws SQLException {
		Departamento departamento = new Departamento(resultSet.getInt("Id"), resultSet.getString("Nome"));
		return departamento;
	}

}
