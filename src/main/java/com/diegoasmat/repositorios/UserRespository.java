package com.diegoasmat.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diegoasmat.modelos.User;

@Repository
public interface UserRespository extends CrudRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
}
