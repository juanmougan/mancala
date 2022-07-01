package com.github.juanmougan.mancala.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Board {

  @Id
  private UUID id;

  @ManyToOne
  private Player south;

  @ManyToOne
  private Player north;

  @ManyToOne
  private Player currentPlayer;

  public void switchPlayer() {
    if (getCurrentPlayer().equals(south)) {
      currentPlayer = north;
    } else {
      currentPlayer = south;
    }
  }
}
