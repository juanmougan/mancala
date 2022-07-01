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
public class Player {

  private UUID id;

  private String name;

  private PlayerType type;
}
