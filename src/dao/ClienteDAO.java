package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {
	
	public void inserir(Cliente cliente) {
		String sql = "INSERT INTO clientes "
				+ "(nome, telefone, email, sexo) VALUES (?,?,?,?)";	
		
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSexo());
			stmt.execute();
			
			stmt.close();
			conexao.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		String sql = "DELETE FROM clientes WHERE id=?";
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
			stmt.close();
			conexao.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cliente> listar(){
		String sql = "SELECT * FROM clientes";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				String nome = resultSet.getString("nome");
				String telefone = resultSet.getString("telefone");
				String email = resultSet.getString("email");
				String sexo = resultSet.getString("sexo");
				int id = resultSet.getInt("id");
				Cliente cliente = new Cliente(id, nome, telefone, email, sexo);
				clientes.add(cliente);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public void atualizar(Cliente cliente) {
		String sql = "UPDATE clientes SET nome=?, "
				+ "telefone=?, email=?, sexo=? WHERE id=?";
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSexo());
			stmt.setInt(5, cliente.getId());
			stmt.executeUpdate();
			stmt.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
