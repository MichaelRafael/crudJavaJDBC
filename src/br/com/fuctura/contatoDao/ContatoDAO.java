package br.com.fuctura.contatoDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Result;

import br.com.fuctura.factory.ConnectionFactory;
import br.com.fuctura.interfaceDAO.InterfaceDAO;
import br.com.fuctura.model.Contato;

public class ContatoDAO implements InterfaceDAO {

	/*
	 * CRUD C: CREATE R: READ U: UPDATE D: DELETE
	 */

	@Override
	public void inserirContato(String cpf) {

		Scanner entrada = new Scanner(System.in);
		PreparedStatement pstm;

		String url = "INSERT INTO contatos (nome, cpf, idade, dataCadastro) VALUES (?, ?, ?, ?)";

		System.out.println("Digite o nome: ");
		String nome = entrada.nextLine();

		System.out.println("Digite sua idade: ");
		Integer idade = entrada.nextInt();
		
		

		Contato contato = new Contato(nome, cpf, idade);

		try {

			pstm = ConnectionFactory.createConnectionToMysql().prepareStatement(url);

			pstm.setString(1, contato.getNome());
			pstm.setString(2, contato.getCpf());
			pstm.setInt(3, contato.getIdade());
			pstm.setTimestamp(4, new java.sql.Timestamp(contato.getDataCadastro().getTime()));

			pstm.execute();
			pstm.close();
			
			
			System.out.println("\nDados inseridos com sucesso!!!");

		} catch (Exception e) {

			System.out.println("\nErro ao inserir os dados " + e.getMessage());
		}
	}

	public boolean ifExisteCPF(String cpf) {

		PreparedStatement pstm;
		ResultSet rs;
		boolean existe = false;

		String url = "SELECT cpf FROM contatos WHERE cpf = ?";

		try {

			pstm = ConnectionFactory.createConnectionToMysql().prepareStatement(url);
			pstm.setString(1, cpf);
			rs = pstm.executeQuery();
			if (rs.next()) {
				existe = true;

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return existe;
	}

	@Override
	public ArrayList<Contato> listarBanco() {

		PreparedStatement pstm;
		ResultSet rs;
		ArrayList<Contato> contatos = new ArrayList<Contato>();

		String url = "SELECT * FROM contatos";

		try {

			pstm = ConnectionFactory.createConnectionToMysql().prepareStatement(url);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				int idade = rs.getInt("idade");
				Date dataCadastro = rs.getDate("dataCadastro");
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
				String dataFormadata = sdf.format(dataCadastro);

				Contato contato = new Contato(nome, cpf, idade, dataFormadata);

				contatos.add(contato);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contatos;
	}

	public void listarBancoInteiro(ArrayList<Contato> contatos) {

		for (Contato contato : contatos) {
			System.out.println(contato);
		}
	}

	@Override
	public void listarContato(String cpf) {
		
		PreparedStatement pstm;
		ResultSet rs;		
		
		String url = "select * from contatos where cpf = ?";
		
		try {
			pstm = ConnectionFactory.createConnectionToMysql().prepareStatement(url);
			pstm.setString(1, cpf);
			rs = pstm.executeQuery();
			
			if (rs.next()) {
				
				String cpf1 = rs.getString("cpf");
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				Date dataCadastro = rs.getDate("dataCadastro");
				
				System.out.println("=========================================\n"
				        +"CPF:     " + cpf +
				        "\nNOME:   " + nome +
				        "\nIDADE:  " + idade +
				        "\nDATA CADASTRO   " + dataCadastro +
				        "\n=========================================");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void atualizarContato(String cpf) {
		
		PreparedStatement pstm;
		String url = "update contatos set nome = ?, idade = ? where cpf = ?";
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Digite o nome: ");
		String nome = entrada.nextLine();

		System.out.println("Digite sua idade: ");
		Integer idade = entrada.nextInt();
		
		try {
			pstm = ConnectionFactory.createConnectionToMysql().prepareStatement(url);
						
			pstm.setString(1, nome);
			pstm.setInt(2, idade);
			pstm.setString(3, cpf);
			
			pstm.execute();
			pstm.close();
			
			System.out.println("\nDados alterados com sucesso!\n");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void excluirContato(String cpf) {
			
		PreparedStatement pstm;
		String url = "delete from contatos where cpf = ?";
		
		try {
			pstm = ConnectionFactory.createConnectionToMysql().prepareStatement(url);
			pstm.setString(1, cpf);
			
			pstm.execute();
			pstm.close();
			
			System.out.println("\nContato excluido com sucesso!!!");
		} catch (Exception e) {
			
			System.out.println("\nErro ao excluir contato!!!");
		}


	}

}
