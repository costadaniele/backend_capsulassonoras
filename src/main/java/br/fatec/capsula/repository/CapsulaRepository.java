package br.fatec.capsula.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.capsula.model.Capsula;

@Repository
public interface CapsulaRepository extends JpaRepository<Capsula, Long> {
	
	@Query("select f from Capsula f order by f.ano")
	Page<Capsula> findAllOrderByAno(Pageable pageable);
	
	@Query("select f from Capsula f join f.integrantes a where a.id = ?1")
	Page<Capsula> findByIntegrante(Pageable pageable, Long integranteId);
	
	Page<Capsula> findByAno(Pageable pageable, Integer ano);
	
	@Query("select count(f) from Capsula f where f.ano between ?1 and ?2")
	Long countByPeriodo(Integer from, Integer to);
}
