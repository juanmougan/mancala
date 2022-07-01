package com.github.juanmougan.mancala.models;

import com.github.juanmougan.mancala.dtos.Board;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Game {

  private UUID id;

  private Board board;

  public void performMove() {
    getBoard().switchPlayer();
  }

}
