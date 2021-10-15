package org.spark.essentials.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.spark.essentials.ApplicationStart;
import org.spark.essentials.domain.Player;
import org.spark.essentials.util.PlayerCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Player Repository")
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    @DisplayName("Save persists player when successful")
    void save_PersistPlayer_WhenSuccessful() {
        Player playerToBeSaved = PlayerCreator.createPlayerToBeSaved();
        Player savedPlayer = this.playerRepository.save(playerToBeSaved);

        Assertions.assertThat(savedPlayer).isNotNull();
        Assertions.assertThat(savedPlayer.getId()).isNotNull();
        Assertions.assertThat(savedPlayer.getName()).isEqualTo(playerToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates player when successful")
    void save_UpdatesPlayer_WhenSuccessful() {
        Player playerToBeSaved = PlayerCreator.createPlayerToBeSaved();
        Player savedPlayer = this.playerRepository.save(playerToBeSaved);

        savedPlayer.setName("Joao");

        Player playerUpdated = this.playerRepository.save(savedPlayer);

        Assertions.assertThat(playerUpdated).isNotNull();
        Assertions.assertThat(playerUpdated.getId()).isNotNull();
        Assertions.assertThat(playerUpdated.getName()).isEqualTo(savedPlayer.getName());
    }

    @Test
    @DisplayName("Delete removes player when successful")
    void delete_RemovesPlayer_WhenSuccessful() {
        Player playerToBeSaved = PlayerCreator.createPlayerToBeSaved();
        Player savedPlayer = this.playerRepository.save(playerToBeSaved);

        this.playerRepository.delete(savedPlayer);

        Optional<Player> playerOptional = this.playerRepository.findById(savedPlayer.getId());
        Assertions.assertThat(playerOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by Name returns list of player when successful")
    void findByName_ReturnsListOfPlayers_WhenSuccessful() {
        Player playerToBeSaved = PlayerCreator.createPlayerToBeSaved();
        Player savedPlayer = this.playerRepository.save(playerToBeSaved);

        String name = savedPlayer.getName();
        List<Player> players = this.playerRepository.findByName(name);

        Assertions.assertThat(players)
                .isNotEmpty()
                .contains(savedPlayer);
    }

    @Test
    @DisplayName("Find by Name returns empty list when no player is found")
    void findByName_ReturnsEmptyList_WhenPlayerIsNotFound() {
        List<Player> players = this.playerRepository.findByName("xaxa");

        Assertions.assertThat(players).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty() {

        Player player = new Player();

//        Assertions.assertThatThrownBy(() -> this.playerRepository.save(player))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.playerRepository.save(player))
                .withMessageContaining("The player name cannot be empty");

    }
}