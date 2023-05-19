package br.edu.ifms.comercioEletronico.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.comercioEletronico.dto.CategoriaDto;
import br.edu.ifms.comercioEletronico.model.Categoria;
import br.edu.ifms.comercioEletronico.model.Produto;
import br.edu.ifms.comercioEletronico.service.CategoriaService;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {
	@Autowired
	CategoriaService categoria;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = categoria.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody CategoriaDto objDto) {
		Categoria obj = categoria.fromDto(objDto);
		obj = categoria.cadastrar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Validated @RequestBody CategoriaDto objDto, @PathVariable Integer id) {
		Categoria obj = categoria.fromDto(objDto);
		obj.setId(id);
		obj = categoria.atualizar(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
		categoria.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> delete(@RequestBody Produto obj, @PathVariable Integer id) {
		categoria.remover(id);
		return ResponseEntity.noContent().build();
	}

//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<CategoriaDto>> findAll() {
//		List<Categoria> list = categoria.buscartodos();
//		List<CategoriaDto> listDto = list.stream().map(obj -> new CategoriaDto(obj.getId(), obj.getNome()))
//				.collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDto);
//	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDto>> findAll() {		
		List<Categoria> list = categoria.buscartodos();
		List<CategoriaDto> listDto = list.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
