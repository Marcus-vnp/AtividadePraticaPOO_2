package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	
	private String dataCadastro;
	private String nome;
	private String telefone;
	private String email;
	private String sexo;
	private int id;
	
	public Cliente(String nome, String telefone, String email, String sexo, String dataCadastro) {
		setNome(nome);
		setTelefone(telefone);
		setEmail(email);
		this.sexo = sexo;
		this.dataCadastro = dataCadastro;
	}
	
	public Cliente(int id, String nome, String telefone, String email, String sexo, String dataCadastro) {
		setNome(nome);
		setTelefone(telefone);
		setEmail(email);
		this.sexo = sexo;
		this.dataCadastro = dataCadastro;
		this.id = id;
		
	}
	
	public int getId() {
		return this.id;
	}
	
	// Validação do Campo Nome
	private boolean verifyNome(String nome){
		Matcher matcher = Pattern.compile("^[A-Za-zÀ-ÿ ]+$").matcher(nome);
		
		if (matcher.find()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	// Validação do Campo Telefone
	private boolean verifyTelefone(String telefone){
		Matcher matcher = Pattern.compile("^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$").matcher(telefone);
		
		if (matcher.find()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	// Validação do Campo Email
		private boolean verifyEmail(String email){
			Matcher matcher = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matcher(email);
			
			if (matcher.find()) {
				return true;
			}else {
				return false;
			}
			
		}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) throws IllegalArgumentException{
		boolean result = verifyNome(nome);
		
		if (result) {
			this.nome = nome;
		}else {
			throw new IllegalArgumentException("Formato do Nome Inválido");
		}
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) throws IllegalArgumentException{
		boolean result = verifyTelefone(telefone);
		
		if (result) {
			this.telefone = telefone;
		}else {
			throw new IllegalArgumentException("Formato de Número de Telefone Inválido");
		}
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) throws IllegalArgumentException{
		boolean result = verifyEmail(email);
		
		if (result) {
			this.email = email;
		}else {
			throw new IllegalArgumentException("Formato de Email Inválido");
		}
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	
	

}
