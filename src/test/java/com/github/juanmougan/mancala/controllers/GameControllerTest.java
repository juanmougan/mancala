package com.github.juanmougan.mancala.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juanmougan.mancala.dtos.GameCreationRequest;
import com.github.juanmougan.mancala.dtos.GameResponse;
import com.github.juanmougan.mancala.dtos.MoveRequest;
import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.services.GameService;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(GameController.class)
@AutoConfigureMockMvc
class GameControllerTest {

  public static final String STARTED_GAME_ID = "00000000-0000-0000-0000-000000000000";
  @MockBean
  private GameService gameService;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldCreateNewGame() throws Exception {
    Mockito.when(gameService.create(any(GameCreationRequest.class)))
        .thenReturn(GameResponse.builder().build());

    MvcResult result =
        mockMvc
            .perform(
                post(Endpoints.GAMES)
                    .content(objectMapper.writeValueAsString(GameCreationRequest.builder().build()))
                    .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andReturn();
  }

  @Test
  void shouldMove() throws Exception {
    Mockito.when(gameService.move(any(UUID.class), any(MoveRequest.class)))
        .thenReturn(GameResponse.builder().build());
    final UUID gameId = UUID.fromString(STARTED_GAME_ID);
    final int startingIndex = 3;
    final MoveRequest moveRequest = MoveRequest.builder()
        .pit(startingIndex)
        .playerType(PlayerType.SOUTH)
        .build();

    final String requestContent = objectMapper.writeValueAsString(moveRequest);
    final MvcResult moveMadeResult = mockMvc.perform(
            patch(Endpoints.GAMES + "/" + gameId)
                .contentType(APPLICATION_JSON).content(requestContent))
        .andExpect(status().isOk())
        .andReturn();
  }

  // TODO generalize
  private GameResponse deserializeResponse(MvcResult result)
      throws UnsupportedEncodingException, JsonProcessingException {
    final String responseAsString = result.getResponse().getContentAsString();
    return objectMapper.readValue(responseAsString, GameResponse.class);
  }
}
