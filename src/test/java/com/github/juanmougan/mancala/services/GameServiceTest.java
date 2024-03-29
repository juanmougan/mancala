package com.github.juanmougan.mancala.services;

import static com.github.juanmougan.mancala.utils.GameMocks.getValidNorthMove;
import static com.github.juanmougan.mancala.utils.GameMocks.getValidSouthMove;
import static com.github.juanmougan.mancala.utils.GameMocks.mockRealGame;
import static com.github.juanmougan.mancala.utils.TestConstants.STARTED_GAME_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.juanmougan.mancala.dtos.GameCreationRequest;
import com.github.juanmougan.mancala.dtos.GameResponse;
import com.github.juanmougan.mancala.dtos.PlayerDto;
import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.dtos.Status;
import com.github.juanmougan.mancala.exceptions.IllegalMovementException;
import com.github.juanmougan.mancala.models.Game;
import com.github.juanmougan.mancala.repositories.GameRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@ExtendWith(MockitoExtension.class)
class GameServiceTest {

  private GameService gameService;

  @BeforeEach
  void setUp() {
    final GameRepository gameRepository = mock(GameRepository.class);
//    final Game game = mockGame();
    final Game game = mockRealGame();
    when(gameRepository.findById(any())).thenReturn(Optional.of(game));
    gameService = new GameService(new BoardService(), new PlayerService(), gameRepository);
  }

  @Test
  void shouldCreateGame() {
    final GameCreationRequest request = GameCreationRequest.builder()
        .playerNorth("north")
        .playerSouth("south")
        .build();

    final GameResponse gameResponse = gameService.create(request);

    assertThat(gameResponse).extracting(GameResponse::getStatus).isEqualTo(Status.STARTED);
    assertThat(gameResponse).extracting(GameResponse::getNext).extracting(PlayerDto::getName)
        .isEqualTo("south");
  }

  @Test
  void shouldMoveWhenValidMoveIsGiven() {
    final int STARTING_PIT = 1;
    final GameResponse moveResult = gameService.move(STARTED_GAME_UUID,
        getValidSouthMove(STARTING_PIT));

    assertThat(moveResult).extracting(GameResponse::getNext).extracting(PlayerDto::getType)
        .isEqualTo(
            PlayerType.NORTH);
  }

  @Test
  void shouldFailIfNotYourTurn() {
    final int STARTING_PIT = 1;
    Assertions.assertThrows(IllegalMovementException.class, () -> {
      gameService.move(STARTED_GAME_UUID,
          getValidNorthMove(STARTING_PIT));
    });
  }
}
