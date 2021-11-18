package br.fatec.capsula.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.capsula.model.Regiao;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
	
	@Query("select r from Regiao r order by r.cidade")
	Page<Regiao> findAllOrderByCidade(Pageable pageable);
	
	Page<Regiao> findByCidadeStartingWith(Pageable pageable, Character letra);
	
}
