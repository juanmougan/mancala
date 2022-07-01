package com.github.juanmougan.mancala.models;

import com.github.juanmougan.mancala.dtos.PlayerType;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Player {

  @Id
  private UUID id;

  private String name;

  private PlayerType type;
}
