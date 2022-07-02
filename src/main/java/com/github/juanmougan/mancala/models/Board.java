package com.github.juanmougan.mancala.models;

import com.github.juanmougan.mancala.dtos.PlayerType;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @OneToMany
  private List<Cell> southCells;

  @OneToMany
  private List<Cell> northCells;

  public void switchPlayer() {
    if (getCurrentPlayer().equals(south)) {
      currentPlayer = north;
    } else {
      currentPlayer = south;
    }
  }

  // TODO refactor these two methods
  public List<Cell> getCellsForPlayer(final Player player) {
    if (PlayerType.SOUTH.equals(player.getType())) {
      return southCells;
    } else {
      if (PlayerType.NORTH.equals(player.getType())) {
        return northCells;
      } else {
        throw new IllegalArgumentException("Unknown player type: " + player.getType());
      }
    }
  }

  public List<Cell> getCellsForRival(final Player player) {
    if (PlayerType.SOUTH.equals(player.getType())) {
      return northCells;
    } else {
      if (PlayerType.NORTH.equals(player.getType())) {
        return southCells;
      } else {
        throw new IllegalArgumentException("Unknown player type: " + player.getType());
      }
    }
  }
}
