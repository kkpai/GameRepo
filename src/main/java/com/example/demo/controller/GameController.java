package com.example.demo.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Game;
import com.example.demo.service.GameService;

import ch.qos.logback.classic.Logger;

@RestController
public class GameController {

	@Autowired
	GameService gameService;

	private Logger logger = (Logger)LoggerFactory.getLogger(GameController.class);
	
	@PostMapping("/game")
	public Game save(@RequestBody Game game) {
		logger.info("Started executing save(..............)");
		return gameService.save(game);
	}

	@GetMapping("/game")
	public List<Game> getGames() {
		logger.info("Started executing getGames()...........");
		return gameService.getAllGames();
	}
	
	@GetMapping("/gameActive")
	public List<Game> getActiveGames(@RequestParam(name = "active") boolean active) {
		logger.info("Started executing getGames()...........");
		System.out.println("going to database .....");
		return gameService.getActiveGames(active);
	}	

}	
