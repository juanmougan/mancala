package com.github.juanmougan.mancala.services;

import com.github.juanmougan.mancala.dtos.Board;
import com.github.juanmougan.mancala.dtos.Player;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardService {

  public Board createInitialBoard(Player south, Player north) {
    return Board.builder()
        .id(UUID.randomUUID())
        .south(south)
        .north(north)
        .currentPlayer(south)
        .build();
  }

  public void changeCurrentPlayer(final Board board) {
    final Player currentPlayer = board.getCurrentPlayer();

  }
}