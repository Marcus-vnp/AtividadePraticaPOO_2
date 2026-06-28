package dao;

import java.sql.Connection;
import java.sql.SQLException;

import util.ConverterData;

public class TestarConexao {

	public static void main(String[] args) {
		
		try {
			ClienteDAO cliente = new ClienteDAO();
			Connection conexao = Conexao.conectar();
			System.out.println("Conectado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao se conectar ao banco");
			e.printStackTrace();
		}
		
		try{
			ConverterData.verificar("01/01/2001");
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
