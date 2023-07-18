package com.example.demo.apiTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.GameController;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class ApiTest {
	@MockBean
	GameService gameService;
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testSaveApi() throws JsonProcessingException, Exception {

		Game testGame1 = new Game();
		testGame1.setId(1);
		testGame1.setName("Monopoly");
		testGame1.setDate("16/07/2023");
		testGame1.setActive(false);

		when(gameService.save(testGame1)).thenReturn(testGame1);

		mockMvc.perform(
				post("/game").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testGame1)))
				.andExpect(status().isOk());

	}

	@Test
	public void testGetApi() throws Exception {

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

		mockMvc.perform(get("/game")).andExpect(status().isOk());

	}

}