package com.github.juanmougan.mancala.dtos;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Board {

  private UUID id;

  private Player south;

  private Player north;

  private Player currentPlayer;

  public void switchPlayer() {
    if (getCurrentPlayer().equals(south)) {
      currentPlayer = north;
    } else {
      currentPlayer = south;
    }
  }
}
