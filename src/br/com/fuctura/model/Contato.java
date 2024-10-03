package br.com.fuctura.model;

import java.sql.Date;
import java.time.LocalDate;

public class Contato {
	
	private int id;	
	private String nome;
	private String cpf;
	private int idade;
	private String dataFormatada;
	private java.sql.Date dataCadastro;		
	
	public Contato() {
		super();
	}
			
	public Contato(String nome, String cpf, int idade) {
		super();		
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.dataCadastro = Date.valueOf(LocalDate.now());
	}
	

	public Contato(String nome, String cpf, int idade, Date dataCadastro) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.dataCadastro = dataCadastro;
	}

	public Contato(String nome, String cpf, int idade, String dataFormatada) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.dataFormatada = dataFormatada;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	@Override
	public String toString() {
		return "=========================================\n"
		        +"CPF:     " + this.cpf +
		        "\nNOME:   " + this.nome +
		        "\nIDADE:  " + this.idade +
		        "\nDATA CADASTRO   " + this.dataFormatada +
		        "\n=========================================";
		        
	}
	
	
			
}
