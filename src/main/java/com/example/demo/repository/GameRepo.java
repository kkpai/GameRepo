package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Game;


public interface GameRepo extends JpaRepository<Game, Integer> {
	
	List<Game> findByActive(boolean active);

}
