package br.edu.ifms.comercioEletronico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.comercioEletronico.dto.ProdutoDto;
import br.edu.ifms.comercioEletronico.model.Produto;
import br.edu.ifms.comercioEletronico.repository.ProdutoRepository;
import br.edu.ifms.comercioEletronico.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto>buscartodos(){
		return produtoRepository.findAll();
	}
	
	public Produto buscarId(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado:" +id+",Tipo:"+Produto.class.getName()));
	}

	public Produto cadastrar(Produto obj) {
		obj.setId(null);
		return produtoRepository.save(obj);
	}

	public Produto atualizar(Produto obj) {
		Produto pro = buscarId(obj.getId());
		pro.setNome(obj.getNome());
		return produtoRepository.save(obj);
	}
		
		public Produto fromDto(ProdutoDto objDto) {
			return new Produto(objDto.getId(),objDto.getNome(), objDto.getMarca(), objDto.getValor(),objDto.getCategoria());

	}

	public void remover(Integer id) {
		buscarId(id);
		produtoRepository.deleteById(id);
	}
}
