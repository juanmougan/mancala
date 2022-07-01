package com.github.juanmougan.mancala.controllers;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.github.juanmougan.mancala.models.Player;
import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.services.GameService;
import com.github.juanmougan.mancala.utils.TestConstants;
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

  @MockBean
  private GameService gameService;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  private static GameResponse expectedGameResponse(final Player northPlayerGame) {
    return GameResponse.builder()
        .next(northPlayerGame)
        .build();
  }

  @Test
  void shouldCreateNewGame() throws Exception {
    final Player northPlayer = Player.builder()
        .name("north")
        .build();
    Mockito.when(gameService.create(any(GameCreationRequest.class)))
        .thenReturn(expectedGameResponse(northPlayer));

    MvcResult result =
        mockMvc
            .perform(
                post(Endpoints.GAMES)
                    .content(objectMapper.writeValueAsString(GameCreationRequest.builder().build()))
                    .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andReturn();

    final GameResponse createdGameResponse = deserializeResponse(result);
    assertThat(createdGameResponse).extracting(GameResponse::getNext).isEqualTo(northPlayer);
  }

  @Test
  void shouldMove() throws Exception {
    Mockito.when(gameService.move(any(UUID.class), any(MoveRequest.class)))
        .thenReturn(GameResponse.builder().build());
    final UUID gameId = UUID.fromString(TestConstants.STARTED_GAME_ID);
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
