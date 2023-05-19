package br.edu.ifms.comercioEletronico.dto;

import br.edu.ifms.comercioEletronico.model.Cliente;

public class ClienteDto {
	
	private Integer id;
	private String nome;
	private String cpf;
	private String endereco;
	private String numero;

	public ClienteDto() {}
	
	
	
	public ClienteDto(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.endereco = obj.getEndereco();
		this.numero = obj.getNumero();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
