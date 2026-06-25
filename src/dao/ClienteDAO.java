package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {
	
	public void inserir(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO clientes (nome, telefone, email, sexo, dataCadastro) VALUES (?,?,?,?,?)";	
		
		Connection conexao = Conexao.conectar();
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getTelefone());
		stmt.setString(3, cliente.getEmail());
		stmt.setString(4, cliente.getSexo());
		stmt.setString(5, cliente.getDataCadastro());
		stmt.execute();
			
		stmt.close();
		conexao.close();
	}
	
	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM clientes WHERE id=?";
		
		Connection conexao = Conexao.conectar();
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
			
		stmt.close();
		conexao.close();
	}
	
	public ArrayList<Cliente> listar() throws SQLException {
		String sql = "SELECT * FROM clientes";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				String nome = resultSet.getString("nome");
				String telefone = resultSet.getString("telefone");
				String email = resultSet.getString("email");
				String sexo = resultSet.getString("sexo");
				String dataCadastro = resultSet.getString("dataCadastro");
				int id = resultSet.getInt("id");
				Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
				clientes.add(cliente);
			}
		
		return clientes;
	}
	
	public void atualizar(Cliente cliente) throws SQLException {
		String sql = "UPDATE clientes SET nome=?, "
				+ "telefone=?, email=?, sexo=?, dataCadastro=? WHERE id=?";
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSexo());
			stmt.setString(5, cliente.getDataCadastro());
			stmt.setInt(6, cliente.getId());
			stmt.executeUpdate();
			stmt.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
