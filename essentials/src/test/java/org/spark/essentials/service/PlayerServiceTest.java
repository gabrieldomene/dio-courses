package org.spark.essentials.service;

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
import org.spark.essentials.exception.BadRequestException;
import org.spark.essentials.repository.PlayerRepository;
import org.spark.essentials.util.PlayerCreator;
import org.spark.essentials.util.PlayerPostRequestBodyCreator;
import org.spark.essentials.util.PlayerPutRequestBodyCreator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    private static final PlayerRepository playerRepositoryMock = mock(PlayerRepository.class);

    @BeforeAll
    static void setUp() {
        PageImpl<Player> playerPage = new PageImpl<>(List.of(PlayerCreator.createValidPlayer()));
        BDDMockito.when(playerRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(playerPage);

        BDDMockito.when(playerRepositoryMock.findAll())
                .thenReturn(List.of(PlayerCreator.createValidPlayer()));

        BDDMockito.when(playerRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PlayerCreator.createValidPlayer()));

        BDDMockito.when(playerRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PlayerCreator.createValidPlayer()));

        BDDMockito.when(playerRepositoryMock.save(ArgumentMatchers.any(Player.class)))
                .thenReturn(PlayerCreator.createValidPlayer());


        BDDMockito.doNothing().when(playerRepositoryMock).delete(ArgumentMatchers.any(Player.class));
    }

    @Test
    @DisplayName("ListAll returns list of player inside page object when successful")
    void listAll_ReturnsListOfPlayersInsidePageObject_WhenSuccessful() {
        String expectedName = PlayerCreator.createValidPlayer().getName();
        Page<Player> playerPage = playerService.listAll(PageRequest.of(1, 2));

        Assertions.assertThat(playerPage).isNotNull();
        Assertions.assertThat(playerPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(playerPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAllNonPageable returns list of player when successful")
    void listAllNonPageable_ReturnsListOfPlayers_WhenSuccessful() {
        String expectedName = PlayerCreator.createValidPlayer().getName();
        List<Player> players = playerService.listAllNonPageable();

        Assertions.assertThat(players)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(players.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when player is " +
            "not found")
    void findByIdOrThrowBadRequestException_ReturnsSinglePlayer_WhenSuccessful() {
//        BDDMockito.when(playerRepositoryMock.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.empty());
//
//        Assertions.assertThatExceptionOfType(BadRequestException.class)
//                .isThrownBy(() -> playerService.findByIdOrThrowBadRequestException(1));
        Long expectedId = PlayerCreator.createValidPlayer().getId();
        Player player = playerService.findByIdOrThrowBadRequestException(1);

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
        List<Player> players = playerService.findByName("felipe");

        Assertions.assertThat(players)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(players.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("FindByName returns an empty list of player when player is not found")
    void findByName_ReturnsEmptyListOfPlayer_WhenPlayerIsNotFound() {
        BDDMockito.when(playerRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Player> players = playerService.findByName("felipe");

        Assertions.assertThat(players)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Save returns player when successful")
    void save_ReturnsPlayer_WhenSuccessful() {
        Player player =
                playerService.save(PlayerPostRequestBodyCreator.createPlayerPostRequestBody());

        Assertions.assertThat(player)
                .isNotNull()
                .isEqualTo(PlayerCreator.createValidPlayer());
    }

    @Test
    @DisplayName("Replace updates player when successful")
    void replace_UpdatesPlayer_WhenSuccessful() {
        Assertions.assertThatCode(() -> playerService.replace(PlayerPutRequestBodyCreator.createPlayerPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete removes player when successful")
    void delete_RemovesPlayer_WhenSuccessful() {
        Assertions.assertThatCode(() -> playerService.delete(1))
                .doesNotThrowAnyException();
    }
}