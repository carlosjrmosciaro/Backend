package br.edu.ifms.comercioEletronico.dto;

import br.edu.ifms.comercioEletronico.model.Produto;
import br.edu.ifms.comercioEletronico.model.Categoria;

public class ProdutoDto {

	private Integer id;
	private String nome;
	private String marca;
	private float valor;
	private Categoria categoria;
	
	public ProdutoDto() {}
	
	public ProdutoDto(Produto obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.marca = obj.getMarca();
		this.valor = obj.getValor();
		this.categoria = obj.getCategoria();
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
