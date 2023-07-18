package com.example.demo.controllerTest;

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

import com.example.demo.controller.GameController;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {
	@Mock
	GameService gameService;

	@InjectMocks
	GameController gameController;

	@Test
	public void testSaveGame() {
		Game testGame1 = new Game();
		testGame1.setId(1);
		testGame1.setName("Monopoly");
		testGame1.setDate("16/07/2023");
		testGame1.setActive(false);

		when(gameService.save(testGame1)).thenReturn(testGame1);
		Game newGame = gameController.save(testGame1);
		assertThat(newGame).isSameAs(testGame1);
		verify(gameService, times(1)).save(testGame1);
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
		when(gameService.getAllGames()).thenReturn(games);
		List<Game> testList = gameController.getGames();

		assertThat(testList).isSameAs(games);
		verify(gameService, times(1)).getAllGames();

	}

}