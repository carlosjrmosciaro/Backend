package br.edu.ifms.comercioEletronico.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.comercioEletronico.dto.ProdutoDto;
import br.edu.ifms.comercioEletronico.model.Produto;
import br.edu.ifms.comercioEletronico.service.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {
	@Autowired
	ProdutoService produto;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = produto.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody ProdutoDto objDto) {
		Produto obj = produto.fromDto(objDto);
		obj = produto.cadastrar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Validated @RequestBody ProdutoDto objDto, @PathVariable Integer id) {
		Produto obj = produto.fromDto(objDto);
		obj.setId(id);
		obj = produto.atualizar(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
		produto.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> delete(@RequestBody Produto obj, @PathVariable Integer id) {
		produto.remover(id);
		return ResponseEntity.noContent().build();
	}
	//falta o trycat aqui - TRATAR EXCEÇÃO DE ERRO
//	public void remover (Integer id) {
//		find(id);
//		try {
//			repo.deleteById(id);
//		}
//		catch (DataIntegrityViolationExcepetion e) {
//			throw new DataIntegrityExcepetion("Não é possível remover. Verifique a integridade referencial!");
//		}
	//}
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<ProdutoDto>> findAll() {
//		List<Produto> list = produto.buscartodos();
//		List<ProdutoDto> listDto = list.stream().map(obj -> new ProdutoDto(obj.getId(), obj.getNome(), obj.getMarca(), obj.getValor(),obj.getCategoria()))
//				.collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDto);
//	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDto>> findAll() {		
		List<Produto> list = produto.buscartodos();
		List<ProdutoDto> listDto = list.stream().map(obj -> new ProdutoDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}	
}
