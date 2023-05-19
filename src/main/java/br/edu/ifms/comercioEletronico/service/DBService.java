package br.edu.ifms.comercioEletronico.service;


import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.comercioEletronico.model.Cliente;
import br.edu.ifms.comercioEletronico.model.Produto;
import br.edu.ifms.comercioEletronico.model.Categoria;
import br.edu.ifms.comercioEletronico.repository.ClienteRepository;
import br.edu.ifms.comercioEletronico.repository.ProdutoRepository;
import br.edu.ifms.comercioEletronico.repository.CategoriaRepository;



@Service
public class DBService {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	
	
	

	public void instantiateTestDatabase() throws ParseException{
		
		
		Categoria cat1= new  Categoria(null, "Eletronicos");			
		Categoria cat2= new  Categoria(null, "Eletrodomesticos");
		Produto produto = new Produto (null, "Celular", "Xiaomi",2.1f, cat1);
		Produto produto1 = new Produto (null, "Celular", "Apple",4.2f, cat1);
		Produto produto2 = new Produto (null, "geladeira frost free", "Eletrolux",2.3f, cat2);
		Cliente cliente = new Cliente (null, "Carlos Junior","00.000.000-00","Ladario - centro", "(67) 9");
		Cliente cliente1 = new Cliente (null, "Gustavo","00.000.000-00","Ladario - centro", "(67) 99892-7099" );
		Cliente cliente2 = new Cliente (null, "Carla","00.000.000-00","Corumba - centro", "(67) 9");
		cliente.getProdutos().addAll(Arrays.asList(produto, produto1));
		cliente1.getProdutos().addAll(Arrays.asList(produto1));
		cliente2.getProdutos().addAll(Arrays.asList(produto2));
		
		
		
		
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(produto,produto1,produto2));
		clienteRepository.saveAll(Arrays.asList(cliente, cliente1, cliente2));
		
		
		
	}

}
