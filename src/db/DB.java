package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conexao = null;
	
	private static Properties carregarPropriedadesDeConexao() {
		try(FileInputStream arquivoDeConexao = new FileInputStream("db.propriedades")){
			Properties propriedadesDeConexao = new Properties();
			propriedadesDeConexao.load(arquivoDeConexao);
			return propriedadesDeConexao;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static Connection iniciarConexao() {
		if (conexao == null) {
			try {
				Properties propriedadesDeConexao = carregarPropriedadesDeConexao();
				String caminhoBD = propriedadesDeConexao.getProperty("dburl");
				conexao = DriverManager.getConnection(caminhoBD, propriedadesDeConexao);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conexao;
	}
	
	public static void fecharConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void fecharStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void fecharResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	
	
	

}
