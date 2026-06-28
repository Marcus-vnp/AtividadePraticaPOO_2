package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Cliente;
import util.ConverterData;

public class ClienteDAO {
	
	public void inserir(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO clientes (nome, telefone, email, sexo, dataCadastro) VALUES (?,?,?,?,?)";	
		
		Connection conexao = Conexao.conectar();
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getTelefone());
		stmt.setString(3, cliente.getEmail());
		stmt.setString(4, cliente.getSexo());
		stmt.setString(5, ConverterData.converter(cliente.getDataCadastro()));
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
				String dataCadastro = ConverterData.retroceder(resultSet.getString("dataCadastro"));
				int id = resultSet.getInt("id");
				Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
				clientes.add(cliente);
			}
		
		return clientes;
	}
	
	public ArrayList<Cliente> listarFeminino() throws SQLException {
		String sql = "SELECT * FROM clientes WHERE sexo = 'Feminino'";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		Connection conexao = Conexao.conectar();
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet resultSet = stmt.executeQuery();
		while(resultSet.next()) {
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			String email = resultSet.getString("email");
			String sexo = resultSet.getString("sexo");
			String dataCadastro = ConverterData.retroceder(resultSet.getString("dataCadastro"));
			int id = resultSet.getInt("id");
			Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	public ArrayList<Cliente> listarMasculino() throws SQLException {
		String sql = "SELECT * FROM clientes WHERE sexo = 'Masculino'";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		Connection conexao = Conexao.conectar();
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet resultSet = stmt.executeQuery();
		while(resultSet.next()) {
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			String email = resultSet.getString("email");
			String sexo = resultSet.getString("sexo");
			String dataCadastro = ConverterData.retroceder(resultSet.getString("dataCadastro"));
			int id = resultSet.getInt("id");
			Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	public void atualizar(Cliente cliente) throws SQLException {
		String sql = "UPDATE clientes SET nome=?, "
				+ "telefone=?, email=?, sexo=?, dataCadastro=? WHERE id=?";
		
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSexo());
			stmt.setString(5, ConverterData.converter(cliente.getDataCadastro()));
			stmt.setInt(6, cliente.getId());
			stmt.executeUpdate();
			stmt.close();
			conexao.close();
	}
	
	public ArrayList<Cliente> buscarPorNome(String nome){
		
		String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");

			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				String telefone = resultSet.getString("telefone");
				String email = resultSet.getString("email");
				String sexo = resultSet.getString("sexo");
				String dataCadastro = ConverterData.retroceder(resultSet.getString("dataCadastro"));
				int id = resultSet.getInt("id");
				Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
				clientes.add(cliente);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public ArrayList<Cliente> buscarPorDataCadastro(String dataCadastro) throws IllegalArgumentException{
		if(!ConverterData.verificar(dataCadastro)) throw new IllegalArgumentException("Formato Invalido!\nUtilize dd/MM/yyyy");
		
		String sql = "SELECT * FROM clientes WHERE dataCadastro LIKE ?";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String dtCadastro = ConverterData.converter(dataCadastro);
		
		
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + dtCadastro + "%");

			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				String nome = resultSet.getString("nome");
				String telefone = resultSet.getString("telefone");
				String email = resultSet.getString("email");
				String sexo = resultSet.getString("sexo");
				int id = resultSet.getInt("id");
				Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
				clientes.add(cliente);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public ArrayList<Cliente> buscarPorPeriodo(String dataInicio, String dataFinal) throws IllegalArgumentException{
		if (dataInicio.trim().isEmpty()) throw new IllegalArgumentException("Data de Inicio Nao Informada!");
		else if (!ConverterData.verificar(dataInicio)) throw new IllegalArgumentException("Data de Inicio esta com Formato Invalido!\nUtilize dd/MM/yyyy");
		
		if (dataFinal.trim().isEmpty()) throw new IllegalArgumentException("Data Final Nao Informada!");
		else if(!ConverterData.verificar(dataFinal)) throw new IllegalArgumentException("Data Final esta com Formato Invalido!\\nUtilize dd/MM/yyyy");
		
		if (!ConverterData.verificarAntecedencia(dataInicio, dataFinal)) throw new IllegalArgumentException("Data de Inicio nao pode ser maior que a Data Final!");
		
		String dtInicio = ConverterData.converter(dataInicio);
		String dtFinal = ConverterData.converter(dataFinal);
		
		String sql = "SELECT * FROM clientes WHERE dataCadastro BETWEEN ? AND ?";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, dtInicio);
			stmt.setString(2, dtFinal);

			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				String nome = resultSet.getString("nome");
				String telefone = resultSet.getString("telefone");
				String email = resultSet.getString("email");
				String sexo = resultSet.getString("sexo");
				String dataCadastro = ConverterData.retroceder(resultSet.getString("dataCadastro"));
				int id = resultSet.getInt("id");
				Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
				clientes.add(cliente);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}

}
