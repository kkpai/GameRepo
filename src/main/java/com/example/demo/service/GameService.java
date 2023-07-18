package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepo;

//Define an interface called GameService to handle game-related operations (crud).
@Service
public class GameService {
	@Autowired
	GameRepo gameRepo;

	public Game save(Game game) {
		return gameRepo.save(game);
	}

	public List<Game> getAllGames() {
		return gameRepo.findAll();
	}
	
	public List<Game> getActiveGames(boolean active) {
		return gameRepo.findByActive(active);
	}
}
