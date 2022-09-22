package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import db.exception.DBException;

public class ConexaoDB {
   
	private static Connection connection = null;
	
	public static Connection getConnection() {
		try {
			Properties properties = loadProperties();
			String urlDB = properties.getProperty("dburl");
			connection = DriverManager.getConnection(urlDB, properties);
		}catch(SQLException error){
           throw new DBException(error.getMessage());
		}
		return connection;
	}
	
	private static Properties loadProperties(){
		try(FileInputStream file = new FileInputStream("C:\\Curso Java\\Codigo Projeto 05\\BancoDadosJDBC\\db.properties")){
			Properties properties = new Properties();
			properties.load(file);
			return properties;
		}catch(IOException error) {
			throw new DBException(error.getMessage());
		}
	}
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
}
