package br.edu.ifms.comercioEletronico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifms.comercioEletronico.dto.CategoriaDto;
import br.edu.ifms.comercioEletronico.model.Categoria;
import br.edu.ifms.comercioEletronico.repository.CategoriaRepository;
import br.edu.ifms.comercioEletronico.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria>buscartodos(){
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarId(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado:" +id+",Tipo:"+Categoria.class.getName()));
	}

	public Categoria cadastrar(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria atualizar(Categoria obj) {
		Categoria categoria = buscarId(obj.getId());
		categoria.setNome(obj.getNome());
		return categoriaRepository.save(obj);
	}
		
		public Categoria fromDto(CategoriaDto obj) {
			return new Categoria(obj.getId(), obj.getNome());

	}

	public void remover(Integer id) {
		buscarId(id);
		categoriaRepository.deleteById(id);
	}
}
