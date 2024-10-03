package br.com.fuctura.interfaceDAO;

import java.util.ArrayList;

import br.com.fuctura.model.Contato;

public interface InterfaceDAO {
	
	public void inserirContato(String cpf);
	public ArrayList<Contato> listarBanco();
	public void listarContato(String cpf);
	public void atualizarContato(String cpf);
	public void excluirContato(String cpf);

}
