package dao;

import java.sql.Connection;
import java.util.ArrayList;

import model.Cliente;
import util.ConverterData;

public class TestarConexao {

	public static void main(String[] args) {
		
		ClienteDAO cliente = new ClienteDAO();
		Connection conexao = Conexao.conectar();
		
		if (conexao != null) {
			System.out.println("Conectado com sucesso!");
		}else {
			System.out.println("Erro ao se conectar ao banco");
		}
		
		try{
			ConverterData.verificar("01/01/2001");
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
