package br.edu.ifms.comercioEletronico.dto;

import br.edu.ifms.comercioEletronico.model.Categoria;

public class CategoriaDto {

	private Integer id;
	private String nome;
	
	public CategoriaDto() {}
	
	
	
	public CategoriaDto(Categoria obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
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
}
