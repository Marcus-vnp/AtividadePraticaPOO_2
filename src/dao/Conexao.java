package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	 
	private static String url = "jdbc:sqlite:clientes.db";
	
	public static Connection conectar() throws SQLException {
		Connection conexao = null;
		//try {
			conexao = DriverManager.getConnection(url);
		//}catch(SQLException e) {
			//e.printStackTrace();
	    //}
		return conexao;				
	}

}
