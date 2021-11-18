package br.fatec.capsula.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.capsula.model.Integrante;

@Repository
public interface IntegranteRepository extends JpaRepository <Integrante, Long>{
	
	Integrante findByLogin(String login);
	
	@Query("select a from Integrante a order by a.nome")
	Page<Integrante> findAllOrderByNome(Pageable pageable);
	
	@Query("select a from Capsula f join f.integrantes a where f.id = ?1")
	Page<Integrante> findByCapsula(Pageable pageable, Long capsulaId);
	
	@Query("select a from Integrante a where a.regiao.id = ?1")
	Page<Integrante> findByRegiao(Pageable pageable, Long regiaoId);
	
	@Query("select count(a) from Integrante a where a.regiao.id = ?1") 
	Long countByRegiao(Long regiaoId);
}
