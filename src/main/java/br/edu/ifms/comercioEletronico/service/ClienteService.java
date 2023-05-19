package br.edu.ifms.comercioEletronico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.comercioEletronico.dto.ClienteDto;
import br.edu.ifms.comercioEletronico.model.Cliente;
import br.edu.ifms.comercioEletronico.repository.ClienteRepository;
import br.edu.ifms.comercioEletronico.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente>buscartodos(){
		return clienteRepository.findAll();
	}
	
	public Cliente buscarId(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado:" +id+",Tipo:"+Cliente.class.getName()));
	}

	public Cliente cadastrar(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}

	public Cliente atualizar(Cliente obj) {
		Cliente cliente = buscarId(obj.getId());
		cliente.setNome(obj.getNome());
		return clienteRepository.save(obj);
	}
		
		public Cliente fromDto(ClienteDto objDto) {
			return new Cliente(objDto.getId(),objDto.getNome(), objDto.getCpf(), objDto.getEndereco(), objDto.getNumero());

	}

	public void remover(Integer id) {
		buscarId(id);
		clienteRepository.deleteById(id);
	}
}
