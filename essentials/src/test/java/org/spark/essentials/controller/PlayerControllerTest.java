package org.spark.essentials.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spark.essentials.domain.Player;
import org.spark.essentials.requests.PlayerPostRequestBody;
import org.spark.essentials.requests.PlayerPutRequestBody;
import org.spark.essentials.service.PlayerService;
import org.spark.essentials.util.PlayerCreator;
import org.spark.essentials.util.PlayerPostRequestBodyCreator;
import org.spark.essentials.util.PlayerPutRequestBodyCreator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class PlayerControllerTest {

    @InjectMocks
    private PlayerController playerController;

    private static final PlayerService playerServiceMock = mock(PlayerService.class);

    @BeforeAll
    static void setUp() {
        PageImpl<Player> playerPage = new PageImpl<>(List.of(PlayerCreator.createValidPlayer()));
        BDDMockito.when(playerServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(playerPage);

        BDDMockito.when(playerServiceMock.listAllNonPageable())
                .thenReturn(List.of(PlayerCreator.createValidPlayer()));

        BDDMockito.when(playerServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(PlayerCreator.createValidPlayer());

        BDDMockito.when(playerServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PlayerCreator.createValidPlayer()));

        BDDMockito.when(playerServiceMock.save(ArgumentMatchers.any(PlayerPostRequestBody.class)))
                .thenReturn(PlayerCreator.createValidPlayer());

        BDDMockito.doNothing().when(playerServiceMock).replace(ArgumentMatchers.any(PlayerPutRequestBody.class));

        BDDMockito.doNothing().when(playerServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("List returns list of player inside page object when successful")
    void list_ReturnsListOfPlayersInsidePageObject_WhenSuccessful() {
        String expectedName = PlayerCreator.createValidPlayer().getName();
        Page<Player> playerPage = playerController.list(null).getBody();

        Assertions.assertThat(playerPage).isNotNull();
        Assertions.assertThat(playerPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(playerPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("ListAll returns list of player when successful")
    void listAll_ReturnsListOfPlayers_WhenSuccessful() {
        String expectedName = PlayerCreator.createValidPlayer().getName();
        List<Player> players = playerController.listAll().getBody();

        Assertions.assertThat(players)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(players.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("FindById returns player when successful")
    void findById_ReturnsSinglePlayer_WhenSuccessful() {
        Long expectedId = PlayerCreator.createValidPlayer().getId();
        Player player = playerController.findById(2).getBody();

        Assertions.assertThat(player)
                .isNotNull();
        Assertions.assertThat(player.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("FindByName returns a list of player when successful")
    void findByName_ReturnsListOfPlayer_WhenSuccessful() {
        String expectedName = PlayerCreator.createValidPlayer().getName();
        List<Player> players = playerController.findByName("felipe").getBody();

        Assertions.assertThat(players)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(players.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("FindByName returns an empty list of player when player is not found")
    void findByName_ReturnsEmptyListOfPlayer_WhenPlayerIsNotFound() {
        BDDMockito.when(playerServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Player> players = playerController.findByName("felipe").getBody();

        Assertions.assertThat(players)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Save returns player when successful")
    void save_ReturnsPlayer_WhenSuccessful() {
        Player player = playerController.saveNewPlayer(PlayerPostRequestBodyCreator.createPlayerPostRequestBody())
                .getBody();

        Assertions.assertThat(player)
                .isNotNull()
                .isEqualTo(PlayerCreator.createValidPlayer());
    }

    @Test
    @DisplayName("Replace updates player when successful")
    void replace_UpdatesPlayer_WhenSuccessful() {
        Assertions.assertThatCode(() -> playerController.replace(PlayerPutRequestBodyCreator.createPlayerPutRequestBody()))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity =
                playerController.replace(PlayerPutRequestBodyCreator.createPlayerPutRequestBody());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("Delete removes player when successful")
    void delete_RemovesPlayer_WhenSuccessful() {
        Assertions.assertThatCode(() -> playerController.delete(1))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity =
                playerController.delete(1);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}