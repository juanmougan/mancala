package com.github.juanmougan.mancala.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CellTest {

  @Test
  void shouldAddOneSeedToOwnPit() {
    final int initialSeeds = 3;
    final Player owner = Mockito.mock(Player.class);
    final Cell cell = Cell.builder()
        .id(UUID.randomUUID())
        .type(CellType.PIT)
        .seeds(initialSeeds)
        .owner(owner)
        .build();

    cell.addOneSeed(owner);

    assertThat(cell).extracting(Cell::getSeeds).isEqualTo(initialSeeds + 1);
  }

  @Test
  void shouldAddOneSeedToRivalPit() {
    final int initialSeeds = 3;
    final Player owner = Mockito.mock(Player.class);
    final Player rival = Mockito.mock(Player.class);
    final Cell cell = Cell.builder()
        .id(UUID.randomUUID())
        .type(CellType.PIT)
        .seeds(initialSeeds)
        .owner(owner)
        .build();

    cell.addOneSeed(rival);

    assertThat(cell).extracting(Cell::getSeeds).isEqualTo(initialSeeds + 1);
  }

  @Test
  void shouldAddOneSeedToOwnScoringWell() {
    final int initialSeeds = 5;
    final Player owner = Mockito.mock(Player.class);
    final Cell cell = Cell.builder()
        .id(UUID.randomUUID())
        .type(CellType.SCORING_WELL)
        .seeds(initialSeeds)
        .owner(owner)
        .build();

    cell.addOneSeed(owner);

    assertThat(cell).extracting(Cell::getSeeds).isEqualTo(initialSeeds + 1);
  }

  @Test
  void shouldNotAddOneSeedToRivalScoringWell() {
    final int initialSeeds = 2;
    final Player owner = Mockito.mock(Player.class);
    final Player rival = Mockito.mock(Player.class);
    final Cell cell = Cell.builder()
        .id(UUID.randomUUID())
        .type(CellType.SCORING_WELL)
        .seeds(initialSeeds)
        .owner(owner)
        .build();

    cell.addOneSeed(rival);

    assertThat(cell).extracting(Cell::getSeeds).isEqualTo(initialSeeds);
  }

  @Test
  void shouldExtractSeedsIfCellIsNotEmpty() {
    final int initialSeeds = 3;
    final Player owner = Mockito.mock(Player.class);
    final Cell cell = Cell.builder()
        .id(UUID.randomUUID())
        .type(CellType.PIT)
        .seeds(initialSeeds)
        .owner(owner)
        .build();

    final int seeds = cell.extractSeeds();

    assertThat(seeds).isEqualTo(initialSeeds);
    assertThat(cell.getSeeds()).isZero();
  }
}
