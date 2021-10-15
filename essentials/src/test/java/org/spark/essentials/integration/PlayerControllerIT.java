package org.spark.essentials.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.spark.essentials.domain.Player;
import org.spark.essentials.repository.PlayerRepository;
import org.spark.essentials.util.PlayerCreator;
import org.spark.essentials.wrapper.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class PlayerControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private PlayerRepository playerRepository;
    @LocalServerPort
    private int port;
    @Test
    @DisplayName("List returns list of player inside page object when successful")
    void list_ReturnsListOfPlayersInsidePageObject_WhenSuccessful(){
        Player savedPlayer = playerRepository.save(PlayerCreator.createPlayerToBeSaved());

        String expectedName = savedPlayer.getName();

        PageableResponse<Player> playerPage = testRestTemplate.exchange("/player", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<Player>>() {
                }).getBody();

        Assertions.assertThat(playerPage).isNotNull();

        Assertions.assertThat(playerPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(playerPage.toList().get(0).getName()).isEqualTo(expectedName);
    }
}
