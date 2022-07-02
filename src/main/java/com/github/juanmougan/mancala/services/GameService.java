package com.github.juanmougan.mancala.services;

import static com.github.juanmougan.mancala.dtos.PlayerType.NORTH;
import static com.github.juanmougan.mancala.dtos.PlayerType.SOUTH;

import com.github.juanmougan.mancala.dtos.GameCreationRequest;
import com.github.juanmougan.mancala.dtos.GameResponse;
import com.github.juanmougan.mancala.dtos.MoveRequest;
import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.dtos.Status;
import com.github.juanmougan.mancala.exceptions.IllegalMovementException;
import com.github.juanmougan.mancala.models.Game;
import com.github.juanmougan.mancala.models.Player;
import com.github.juanmougan.mancala.repositories.GameRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameService {

  private final ModelMapper modelMapper = new ModelMapper();

  private final BoardService boardService;

  private final PlayerService playerService;

  private final GameRepository gameRepository;

  public GameResponse create(final GameCreationRequest request) {
    final Player south = playerService.create(request.getPlayerSouth(), SOUTH);
    return GameResponse.builder()
        .status(Status.STARTED)
        .board(boardService.createInitialBoard(
            south,
            playerService.create(request.getPlayerNorth(), NORTH)))
        .next(south)
        .build();
  }

  public GameResponse move(final UUID gameId, final MoveRequest moveRequest) {
    // TODO get the Game from the DB
    // TODO create a business exception here, handle in a ControllerAdvice
    final Game currentGame = gameRepository.findById(gameId).orElseThrow();
    return performMove(currentGame, moveRequest);
  }

  private GameResponse performMove(final Game currentGame, final MoveRequest moveRequest) {
    validateYourTurn(currentGame, moveRequest);
    currentGame.switchCurrentPlayer();
    return GameResponse.builder()
        .id(currentGame.getId())
        .board(currentGame.getBoard())
        .next(currentGame.getBoard().getCurrentPlayer())
        .status(Status.STARTED)
        .build();
  }

  private void validateYourTurn(final Game currentGame, final MoveRequest moveRequest) {
    final PlayerType currentPlayerType = currentGame.getBoard().getCurrentPlayer().getType();
    if (!currentPlayerType.equals(moveRequest.getPlayerType())) {
      throw new IllegalMovementException(String.format("It's %s turn!", currentPlayerType));
    }
  }
}
