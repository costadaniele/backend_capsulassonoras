package br.fatec.capsula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.fatec.capsula.model.Capsula;
import br.fatec.capsula.repository.CapsulaRepository;

@Service
public class CapsulaService implements ServiceInterface<Capsula> {
	
	@Autowired
	private CapsulaRepository repo;
	
	@Override
	public Capsula create(Capsula obj) {
		repo.save(obj);
		return obj;
	}
	
	@Override
	public Capsula findById(Long id) {
		Optional<Capsula> _capsula = repo.findById(id);		
		return _capsula.orElse(null);
	}

	@Override
	public List<Capsula> findAll() {
		return repo.findAll();
	}
	
	public Page<Capsula> findAll(Pageable pageable) {
		return repo.findAllOrderByAno(pageable);
	}

	public Page<Capsula> findByIntegrante(Pageable pageable, Long integranteId) {
		return repo.findByIntegrante(pageable, integranteId);
	}

	public Page<Capsula> findByAno(Pageable pageable, Integer ano) {
		return repo.findByAno(pageable, ano);
	}
	
	public Long countByPeriodo(Integer from, Integer to) {
		return repo.countByPeriodo(from, to);
	}
	
	@Override
	public boolean update(Capsula obj) {
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
