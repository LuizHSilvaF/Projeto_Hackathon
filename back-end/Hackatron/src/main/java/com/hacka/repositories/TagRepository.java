package com.hacka.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hacka.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

	@Query("SELECT t FROM Tag t WHERE t.nome = :nome")
	Optional<Tag> findByNome(@Param("nome") String nome);
}
