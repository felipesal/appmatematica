package com.felipesalles.appmatematica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipesalles.appmatematica.domain.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer>{

}
