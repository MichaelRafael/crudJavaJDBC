package br.com.fuctura;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.fuctura.contatoDao.ContatoDAO;
import br.com.fuctura.model.Contato;

public class Aplicacao {
	
	@SuppressWarnings("finally")
	public static int leiaInt(String num) {
		
		int n = 0;
		
		
		while (true) {
			try {
				n = Integer.valueOf(num);				
			} catch (Exception e) {
				System.out.println("ERRO! Digite apenas números inteiros");
			} finally {
				return n;
			}
		}
	}	
	
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub

		Scanner entrada = new Scanner(System.in);
		int opc;

		while (true) {

			System.out.println("   ESCOLHA UMA OPÇÃO ");
			System.out.println("|--------------------|");
			System.out.println("| 1-Inserir contato  |");
			System.out.println("| 2- Listar Banco    |");
			System.out.println("| 3- Listar contato  |");
			System.out.println("| 4- Alterar contato |");
			System.out.println("| 5- Excluir contato |");
			System.out.println("| 6- Sair            |");
			System.out.println("|--------------------|");

			System.out.println("Opção: ");
			opc = leiaInt(entrada.next());

			if (opc == 1) {

				System.out.println("#### INSERIR CONTATO ####\n");

				System.out.println("\nDigite o cpf ");
				String cpf = entrada.next();

				ContatoDAO contatoDAO = new ContatoDAO();
				boolean existe;

				existe = contatoDAO.ifExisteCPF(cpf);

				if (existe) {
					System.out.println("\nCPF já cadastro no banco de dados");

				} else {
					contatoDAO.inserirContato(cpf);

				}
			} else if (opc == 2) {

				System.out.println("#### LISTAR BANCO ####\n");

				ContatoDAO contatoDAO = new ContatoDAO();
				ArrayList<Contato> contatos = new ArrayList<>();

				contatos = contatoDAO.listarBanco();
				contatoDAO.listarBancoInteiro(contatos);

			} else if (opc == 3) {
				
				System.out.println("#### LISTAR CONTATO ####\n");

				System.out.println("\nDigite o cpf ");
				String cpf = entrada.next();
				
				ContatoDAO contatoDao = new ContatoDAO();
				boolean existe;
				
				existe = contatoDao.ifExisteCPF(cpf);
				
				if(!existe) {
					System.out.println("Cpf não cadastro na base de dados");
				
				} else {
					contatoDao.listarContato(cpf);
				}
			} else if ( opc == 4) {
				
				System.out.println("#### ALTERAR CONTATO ####\n");

				System.out.println("\nDigite o cpf ");
				String cpf = entrada.next();
				
				ContatoDAO contatoDao = new ContatoDAO();
				boolean existe;
				
				existe = contatoDao.ifExisteCPF(cpf);
				
				if(!existe) {
					System.out.println("Cpf não cadastro na base de dados");
				
				} else {
					contatoDao.atualizarContato(cpf);
				}
			} else if ( opc == 5 ) {
				
				System.out.println("#### EXCLUIR CONTATO ####\n");

				System.out.println("\nDigite o cpf ");
				String cpf = entrada.next();
				
				ContatoDAO contatoDao = new ContatoDAO();
				boolean existe;
				
				existe = contatoDao.ifExisteCPF(cpf);
				
				if (existe) {
					
					System.out.println("Certeza que deseja excluir o cpf " + cpf + " [S/N]".toUpperCase());
					String resp = entrada.next();
					
					if (resp.equalsIgnoreCase("N")) {
						System.out.println("\nTUDO BEM\n");
					
					} else if (resp.equalsIgnoreCase("S")) {
						contatoDao.excluirContato(cpf);
						
					} else {
						System.out.println("\nOpção inválida");
					}
				} else {
					System.out.println("\nCPF inexistente na base de dados!!!\n");
				}
			} else if ( opc == 6 ) {
				System.out.println("SAINDO... ATÉ MAIS!");
				break;
			} else {
				System.out.println("\nOpção inválida, tente novamente!\n");
			}
		}
	}
}
