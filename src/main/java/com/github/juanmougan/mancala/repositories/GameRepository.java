package com.github.juanmougan.mancala.repositories;

import com.github.juanmougan.mancala.models.Game;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, UUID> {

}
