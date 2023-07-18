package com.example.demo.aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.cachememory.CacheList;
import com.example.demo.controller.GameController;
import com.example.demo.model.Game;

import ch.qos.logback.classic.Logger;

@Aspect
@Component
public class CacheAop {

	private Logger logger = (Logger) LoggerFactory.getLogger(GameController.class);

	@Around("execution(* com.example.demo.controller..*(..))")
	public List<Game> cache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		List<Game> games;
		List<Game> filteredGames = new ArrayList<Game>();
		Boolean activeGameValue = (Boolean) proceedingJoinPoint.getArgs()[0];
		games = CacheList.get();

		logger.info("===========    CacheList : " + games.toArray().toString());

		boolean hasMatchingActiveGameValue = hasMatchingActiveGameValue(games, activeGameValue);
		if (hasMatchingActiveGameValue) {
			logger.info("getting from cache .....");
			for (Game game : games) {
				logger.info("In loop .....");
				if (game.isActive() == activeGameValue.booleanValue()) {
					filteredGames.add(game);
				}
			}
			return filteredGames;
		} else {
			logger.info("getting from database .....");
			List<Game> gamesList = (List<Game>) proceedingJoinPoint.proceed();
			CacheList.save(gamesList);
			logger.info("Saved list : " + CacheList.get());
			return gamesList;
		}
	}

	private boolean hasMatchingActiveGameValue(List<Game> games, Boolean activeGame) {
		boolean hasActiveGame = false;

		for (Game game : games) {
			logger.info("In loop .....");
			if (game.isActive() == activeGame.booleanValue()) {
				hasActiveGame = true;
				break;
			}
		}
		return hasActiveGame;
	}

}
