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
public class Game {

  @Id
  private UUID id;

  @ManyToOne
  private Board board;

  public void switchCurrentPlayer() {
    getBoard().switchPlayer();
  }

}
