package com.loginproj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginproj.model.User;

@Repository
public interface userRepo extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
	Boolean existsByusername(String username);
	Boolean existsByEmail(String username);
}
