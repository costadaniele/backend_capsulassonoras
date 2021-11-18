package br.fatec.capsula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.fatec.capsula.model.Regiao;
import br.fatec.capsula.repository.RegiaoRepository;

@Service
public class RegiaoService implements ServiceInterface <Regiao>{
	
	@Autowired
	private RegiaoRepository repo;
	
	@Override
	public Regiao create(Regiao obj) {
		return repo.save(obj);
	}

	@Override
	public Regiao findById(Long id) {
		Optional<Regiao> _reg = repo.findById(id);		
		return _reg.orElse(null);
	}
	
	@Override
	public List<Regiao> findAll() {
		return repo.findAll();
	}
	
	
	public Page<Regiao> findAll(Pageable pageable) {
		return repo.findAllOrderByCidade(pageable);
	}
	
	public Page<Regiao> findByCidade(Pageable pageable, Character letra) {
		return repo.findByCidadeStartingWith(pageable, letra);
	}
	
	

	@Override
	public boolean update(Regiao obj) {				
		if (repo.existsById(obj.getId())) {
			repo.save(obj);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(Long id) {	
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

}
