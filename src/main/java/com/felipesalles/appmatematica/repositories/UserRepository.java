package com.felipesalles.appmatematica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipesalles.appmatematica.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
