package com.example.demo.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepo;
import com.example.demo.service.GameService;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
	@Mock
	GameRepo gameRepo;
	@InjectMocks
	GameService gameService;

	@Test
	public void testSaveGame() {
		Game testGame = new Game();

		testGame.setId(1);
		testGame.setName("Monopoly");
		testGame.setDate("16/07/2023");
		testGame.setActive(false);

		when(gameRepo.save(testGame)).thenReturn(testGame);
		Game newGame = gameService.save(testGame);
		assertThat(newGame).isSameAs(testGame);
		verify(gameRepo, times(1)).save(testGame);
	}

	@Test
	public void testFindAll() {

		Game testGame1 = new Game();
		testGame1.setId(1);
		testGame1.setName("Monopoly");
		testGame1.setDate("16/07/2023");
		testGame1.setActive(false);

		Game testGame2 = new Game();
		testGame2.setId(1);
		testGame2.setName("Chess");
		testGame2.setDate("17/07/2023");
		testGame2.setActive(true);

		List<Game> games = new ArrayList<>();
		games.add(testGame1);
		games.add(testGame2);
		when(gameRepo.findAll()).thenReturn(games);
		List<Game> testList = gameService.getAllGames();
		
		assertThat(testList).isSameAs(games);
		verify(gameRepo, times(1)).findAll();

	}

}