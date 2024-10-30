package com.hacka.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hacka.entities.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

	@Query("SELECT p FROM Portfolio p WHERE p.criador.id = :criadorId")
    List<Portfolio> meusProjetos(Long criadorId);
	
	@Query("SELECT p FROM Portfolio p JOIN p.tag t WHERE t.id = :tagId")
    List<Portfolio> pesquisaTag(@Param("tagId") Long tagId);
}
