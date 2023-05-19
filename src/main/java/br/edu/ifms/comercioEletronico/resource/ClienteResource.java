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

import br.edu.ifms.comercioEletronico.service.ClienteService;
import br.edu.ifms.comercioEletronico.dto.ClienteDto;
import br.edu.ifms.comercioEletronico.model.Cliente;
import br.edu.ifms.comercioEletronico.model.Produto;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

	@Autowired
	ClienteService cliente;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = cliente.buscarId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody ClienteDto objDto) {
		Cliente obj = cliente.fromDto(objDto);
		obj = cliente.cadastrar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Validated @RequestBody ClienteDto objDto, @PathVariable Integer id) {
		Cliente obj = cliente.fromDto(objDto);
		obj.setId(id);
		obj = cliente.atualizar(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
		cliente.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> delete(@RequestBody Produto obj, @PathVariable Integer id) {
		cliente.remover(id);
		return ResponseEntity.noContent().build();
	}

//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<ClienteDto>> findAll() {
//		List<Cliente> list = cliente.buscartodos();
//		List<ClienteDto> listDto = list.stream().map(obj -> new ClienteDto(obj.getId(), obj.getNome(),obj.getCpf(), obj.getEndereco(), obj.getNumero()))
//				.collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDto);
//	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDto>> findAll() {		
		List<Cliente> list = cliente.buscartodos();
		List<ClienteDto> listDto = list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
