package br.fatec.capsula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.capsula.model.Integrante;
import br.fatec.capsula.repository.IntegranteRepository;

@Service
public class IntegranteService implements ServiceInterface<Integrante>{
	
	@Autowired
	private IntegranteRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Integrante create(Integrante obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		repo.save(obj);
		return obj;
	}
	
	@Override
	public Integrante findById(Long id) {
		Optional<Integrante> _integrante = repo.findById(id);		
		return _integrante.orElse(null);
	}

	@Override
	public List<Integrante> findAll() {
		return repo.findAll();
	}
	
	public Page<Integrante> findAll(Pageable pageable) {
		return repo.findAllOrderByNome(pageable);
	}

	public Page<Integrante> findByCapsula(Pageable pageable, Long capsulaId) {
		return repo.findByCapsula(pageable, capsulaId);
	}
	
	public Page<Integrante> findByRegiao(Pageable pageable, Long regiaoId) {
		return repo.findByRegiao(pageable, regiaoId);
	}

	public Long countByRegiao(Long regiaoId) {
		return repo.countByRegiao(regiaoId);
	}
	
	@Override
	public boolean update(Integrante obj) {
		if (repo.existsById(obj.getId())) {
			obj.setSenha(passwordEncoder.encode(obj.getSenha()));
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
