package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;
import model.Cliente;
import model.ClienteTableModel;
import util.DadosMockados;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textTelefone;
	private JTextField textBuscarNome;
	private JTable table;
	private ClienteTableModel modelo;
	private ArrayList<Cliente> clientes;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private ClienteDAO dao;
	private JTextField textBuscarDataCadastro;
	private JTextField textPeriodoInicio;
	private JTextField textPeriodoFinal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		dao = new ClienteDAO();
		clientes = new ArrayList<Cliente>();
		try {
			//DadosMockados.carregar(clientes);
			modelo = new ClienteTableModel(dao.listar());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível carregar os clientes.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 836);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image = new ImageIcon(TelaCadastro.class.getResource("/img/cadastro.png"));
		Image imageScaled = image.getImage();
		Image novaImg = imageScaled.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(novaImg));
		lblNewLabel.setBounds(23, 35, 112, 92);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CADASTRO DE CLIENTES");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(280, 63, 209, 24);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 127, 690, 158);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBounds(12, 29, 339, 35);
		panel.add(textNome);
		textNome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(12, 99, 339, 35);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(393, 29, 285, 35);
		panel.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(12, 12, 60, 17);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(12, 80, 60, 17);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setBounds(394, 12, 60, 17);
		panel.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(393, 99, 285, 35);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBackground(new Color(255, 255, 255));
		rdbtnMasculino.setBounds(0, 8, 130, 25);
		panel_2.add(rdbtnMasculino);
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBackground(new Color(255, 255, 255));
		rdbtnFeminino.setBounds(155, 8, 130, 25);
		panel_2.add(rdbtnFeminino);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnFeminino);
		buttonGroup.add(rdbtnMasculino);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(23, 299, 690, 147);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		//ButtonAction action = new ButtonAction();
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText().toString();
				String email = textEmail.getText().toString();
				String telefone = textTelefone.getText().toString();
				String sexo = rdbtnMasculino.isSelected() ? "Masculino" : "Feminino";
				LocalDate hoje = LocalDate.now();
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String dataCadastro = hoje.format(formato);
				if (nome.isBlank() || email.isBlank() || telefone.isBlank() 
						|| sexo.isBlank()) {
					JOptionPane.showMessageDialog(TelaCadastro.this, 
							"Preencha todos os campos", "Alerta", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					
					try {
						Cliente cliente = new Cliente(nome, telefone, email, sexo, dataCadastro);
						dao.inserir(cliente);
						modelo.addCliente(cliente);	
						
						JOptionPane.showMessageDialog(TelaCadastro.this, "Cliente adicionado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					} catch (IllegalArgumentException er) {
						JOptionPane.showMessageDialog(TelaCadastro.this, er.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível inserir esse cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				textNome.setText("");
				textTelefone.setText("");
				textEmail.setText("");
				buttonGroup.clearSelection();
				
			}
			
		});
		btnSalvar.setBounds(23, 25, 105, 27);
		panel_1.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indice = table.getSelectedRow();
				if (indice >= 0) {
					Cliente cliente = modelo.getCliente(indice);
					
					try {
						dao.excluir(cliente.getId());
						modelo.removerCliente(indice);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível excluir esse cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Selecione o cliente a ser excluído.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnExcluir.setBounds(153, 25, 105, 27);
		panel_1.add(btnExcluir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscarNome = textBuscarNome.getText().toString();
				String buscarDataCadastro = textBuscarDataCadastro.getText().toString();
				String buscarDataInicio = textPeriodoInicio.getText().toString();
				String buscarDataFinal = textPeriodoFinal.getText().toString();
				if (!buscarNome.isBlank()) {
					ArrayList<Cliente> clientes = dao.buscarPorNome(buscarNome);

					modelo.atualizarTabela(clientes);
				}else if(!buscarDataCadastro.isBlank()) {
					try {
					ArrayList<Cliente> clientes = dao.buscarPorDataCadastro(buscarDataCadastro);
					modelo.atualizarTabela(clientes);
					}catch(IllegalArgumentException er){
						JOptionPane.showMessageDialog(TelaCadastro.this, er.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
					}
				}else if(!buscarDataInicio.isBlank() || !buscarDataFinal.isBlank()){
					try {
						ArrayList<Cliente> clientes = dao.buscarPorPeriodo(buscarDataInicio, buscarDataFinal);
						modelo.atualizarTabela(clientes);
					}catch(IllegalArgumentException er){
						JOptionPane.showMessageDialog(TelaCadastro.this, er.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
					}

				}else {
					try {
						modelo.atualizarTabela(dao.listar());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnBuscar.setBounds(278, 25, 105, 27);
		panel_1.add(btnBuscar);
		
		textBuscarNome = new JTextField();
		textBuscarNome.setBounds(409, 38, 269, 27);
		panel_1.add(textBuscarNome);
		textBuscarNome.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Nome");
		lblNewLabel_5.setBounds(408, 20, 60, 17);
		panel_1.add(lblNewLabel_5);
		
		textBuscarDataCadastro = new JTextField();
		textBuscarDataCadastro.setColumns(10);
		textBuscarDataCadastro.setBounds(409, 108, 269, 27);
		panel_1.add(textBuscarDataCadastro);
		
		JLabel lblNewLabel_6 = new JLabel("dataCadastro [dd/mm/yyyy]");
		lblNewLabel_6.setBounds(408, 89, 198, 17);
		panel_1.add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 580, 690, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 737, 23);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnNewMenu.add(mntmAbrir);
		mntmAbrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jFileChooser = new JFileChooser();
				if (jFileChooser.showOpenDialog(TelaCadastro.this) == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					
					carregarDados(file, modelo);
						
				}
				
			}
		});
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnNewMenu.add(mntmSalvar);
		mntmSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				if (jFileChooser.showSaveDialog(TelaCadastro.this) == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					salvarDados(file, modelo);
				}
				
			}
		});
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmSair);
		
		
		JMenu mnNewMenuEditar = new JMenu("Editar");
		menuBar.add(mnNewMenuEditar);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				if (linha < 0) {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Selecione um cliente para atualizar!", 
							"Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}else {
					Cliente cliente = modelo.getCliente(linha);
					TelaAtualizar dialogo = new TelaAtualizar(TelaCadastro.this, cliente);
					dialogo.setVisible(true);
					if (dialogo.getClienteEditado() != null) {
						try {
							dao.atualizar(dialogo.getClienteEditado());
							modelo.atualizarTabela(dao.listar());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível atualizar esse cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		mnNewMenuEditar.add(mntmAtualizar);
		
		JMenu mnFerramentas = new JMenu("Ferramentas");
		menuBar.add(mnFerramentas);
		
		JMenuItem mntmValidar = new JMenuItem("Validar dados");
		mnFerramentas.add(mntmValidar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar relatorio");
		mntmExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				
				if (jFileChooser.showSaveDialog(TelaCadastro.this) == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					
					exportarRelatorio(file, modelo);
				}
			}
		});
		mnFerramentas.add(mntmExportar);
		
		JMenuItem mntmImportar = new JMenuItem("Importar CSV validado");
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				if (jFileChooser.showSaveDialog(TelaCadastro.this) == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					importarCSVValidado(file);
				}
			}
		});
		mnFerramentas.add(mntmImportar);
		
		JMenu mnNewMenu_3 = new JMenu("Sobre");
		menuBar.add(mnNewMenu_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(23, 453, 221, 115);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		textPeriodoInicio = new JTextField();
		textPeriodoInicio.setBounds(91, 36, 114, 21);
		panel_3.add(textPeriodoInicio);
		textPeriodoInicio.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Data Final");
		lblNewLabel_7.setBounds(12, 71, 61, 17);
		panel_3.add(lblNewLabel_7);
		
		textPeriodoFinal = new JTextField();
		textPeriodoFinal.setBounds(91, 69, 114, 21);
		panel_3.add(textPeriodoFinal);
		textPeriodoFinal.setColumns(10);
		
		JLabel lblNewLabel_7_1 = new JLabel("Data Inicio");
		lblNewLabel_7_1.setBounds(12, 38, 65, 17);
		panel_3.add(lblNewLabel_7_1);
		
		JLabel lblPesquisaPorPeriodo = new JLabel("Pesquisa Por Periodo");
		lblPesquisaPorPeriodo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPesquisaPorPeriodo.setBounds(32, 7, 157, 17);
		panel_3.add(lblPesquisaPorPeriodo);
	}
	
	private void carregarDados(File file, ClienteTableModel modelo) {
		int contador = 0;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			modelo.limparDados();
			String linha = bufferedReader.readLine();
			
			if(linha == null) {
				JOptionPane.showMessageDialog(TelaCadastro.this, "O arquivo está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			
			while((linha = bufferedReader.readLine()) != null) {
				String campos [] = linha.split(",");
				if (campos.length == 5) {
					String nome = campos[0];
					String telefone = campos[1];
					String email = campos[2];
					String sexo = campos[3];
					String dataCadastro = campos[4];
					Cliente cliente = new Cliente(nome, telefone, email, sexo, dataCadastro);
					modelo.addCliente(cliente);
				} else {
					contador++;
				}
			}
		}catch(IOException e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Não encontrei esse arquivo", "Aviso", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
				fileReader.close();				
			}catch(IOException e) {
				JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível fechar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}		
		
		if(contador > 0) {
			JOptionPane.showMessageDialog(TelaCadastro.this, contador + " cliente(s) com quantidade incorreta de campos", "Aviso", JOptionPane.WARNING_MESSAGE);			
		}
	}
	
	private void salvarDados(File file, ClienteTableModel modelo) {
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("Nome,Telefone,Email,Sexo");
			bufferedWriter.newLine();
			for (int i=0; i < modelo.getRowCount(); i++) {
				String nome = (String) modelo.getValueAt(i, 0);
				String telefone = (String) modelo.getValueAt(i, 1);
				String email = (String) modelo.getValueAt(i, 2);
				String sexo = (String) modelo.getValueAt(i, 3);
				String dataCadastro = (String) modelo.getValueAt(i, 4);
				bufferedWriter.write(nome+","+telefone+","+
				","+email+","+sexo+","+dataCadastro);
				bufferedWriter.newLine();
			}
		}catch(IOException e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível salvar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {				
				bufferedWriter.close();
				fileWriter.close();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível fechar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
	}
	
	private boolean validarCliente(Cliente cliente) {
		return false;
	}
	
	private void exportarRelatorio(File arquivo, ClienteTableModel modelo) {
		try {
			
			fileWriter = new FileWriter(arquivo);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("RELATÓRIO DE CLIENTES");
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			
			LocalDate hoje = LocalDate.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataHoje = hoje.format(formato);
			
			bufferedWriter.write("Data de geração: " + dataHoje);
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			
			bufferedWriter.write("Total de clientes: " + dao.listar().size());
			bufferedWriter.newLine();
			bufferedWriter.write("Masculino: " + 0);
			bufferedWriter.newLine();
			bufferedWriter.write("Feminino: " + 0);
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			
			for(int i = 0; i < modelo.getRowCount(); i++) {
				String nome = (String) modelo.getValueAt(i, 0);
				String telefone = (String) modelo.getValueAt(i, 1);
				String email = (String) modelo.getValueAt(i, 2);
				String sexo = (String) modelo.getValueAt(i, 3);
				String dataCadastro = (String) modelo.getValueAt(i, 4);
				
				bufferedWriter.write("----------------------------------");
				bufferedWriter.newLine();
				bufferedWriter.write("Nome: " + nome);
				bufferedWriter.newLine();
				bufferedWriter.write("Telefone: " + telefone);
				bufferedWriter.newLine();
				bufferedWriter.write("Email: " + email);
				bufferedWriter.newLine();
				bufferedWriter.write("Sexo: " + sexo);
				bufferedWriter.newLine();
				bufferedWriter.write("Data de Cadastro: " + dataCadastro);
				bufferedWriter.newLine();
				
			}
			
			bufferedWriter.write("----------------------------------");
			bufferedWriter.newLine();
			
			JOptionPane.showMessageDialog(TelaCadastro.this, "Relatório exportado com sucesso.", "Concluído", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Erro ao exportar relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Erro no Banco de Dados ao exportar relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			try {				
				bufferedWriter.close();
				fileWriter.close();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível fechar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
	}
	
	private void importarCSVValidado(File arquivo) {
		try {
			fileReader = new FileReader(arquivo);
			bufferedReader = new BufferedReader(fileReader);
			String linha = bufferedReader.readLine();
			int aceitos = 0;
			int rejeitados = 0;
			
			if(arquivo.length() == 0 || linha == null) {
				JOptionPane.showMessageDialog(TelaCadastro.this, "O arquivo selecionado está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			
			while((linha = bufferedReader.readLine()) != null) {
				String campos [] = linha.split(",");
				if (campos.length == 4) {
					String nome = campos[0];
					String telefone = campos[1];
					String email = campos[2];
					String sexo = campos[3];
					LocalDate hoje = LocalDate.now();
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String dataCadastro = hoje.format(formato);
					
					Cliente cliente = new Cliente(nome, telefone, email, sexo, dataCadastro);
					dao.inserir(cliente);
					modelo.addCliente(cliente);
					
					aceitos++;
				} else {
					rejeitados++;
				}
			}
			
			JOptionPane.showMessageDialog(TelaCadastro.this, "Registros importados: " + aceitos + "\nRegistros rejeitados: " + rejeitados, "Importação concluída", JOptionPane.INFORMATION_MESSAGE);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Não encontrei esse arquivo", "Aviso", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch (SQLException e){
			JOptionPane.showMessageDialog(TelaCadastro.this, "Erro no Banco de Dados ao inserir cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
				fileReader.close();				
			}catch(IOException e) {
				JOptionPane.showMessageDialog(TelaCadastro.this, "Não foi possível fechar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}	
	}
}