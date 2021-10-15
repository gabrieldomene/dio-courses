package org.spark.essentials.client;

import lombok.extern.log4j.Log4j2;
import org.spark.essentials.domain.Player;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {

    public static void main(String[] args) {
//        ResponseEntity<Player> entity = new RestTemplate().getForEntity("http://localhost:8080" +
//                "/player/{id}", Player.class, 2);
//        log.info(entity);
//
//        Player object = new RestTemplate().getForObject("http://localhost:8080/player/{id}",
//                Player.class, 6);
//        log.info(object);
//
//        Player[] players = new RestTemplate().getForObject("http://localhost:8080/player/all",
//                Player[].class);
//        log.info(Arrays.toString(players));
//
//        ResponseEntity<List<Player>> exchange = new RestTemplate().exchange("http://localhost:8080/player/all",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Player>>() {});
//        log.info(exchange.getBody());

//        Player playerOne = Player.builder().name("Nick").build();
//        Player playerSaved = new RestTemplate().postForObject("http://localhost:8080/player/create",
//                playerOne, Player.class);
//        log.info("saved {}", playerSaved);

        Player playerOne = Player.builder().name("Jackson").build();
        ResponseEntity<Player> playerSaved = new RestTemplate().exchange("http://localhost:8080" +
                        "/player" +
                        "/create",
                HttpMethod.POST,
                new HttpEntity<>(playerOne, createJsonHeader()),
                Player.class);
        log.info("saved {}", playerSaved);

        Player playerToBeUpdated = playerSaved.getBody();
        playerToBeUpdated.setName("Jackson Five");

        ResponseEntity<Void> playerUpdated = new RestTemplate().exchange("http://localhost:8080" +
                        "/player", HttpMethod.PUT,
                new HttpEntity<>(playerToBeUpdated, createJsonHeader()), Void.class);

        log.info("updated {}", playerUpdated);

        ResponseEntity<Void> playerDeleted = new RestTemplate().exchange("http://localhost:8080" +
                        "/player/{id}", HttpMethod.DELETE,
                null, Void.class, playerToBeUpdated.getId());

        log.info("deleted {}", playerDeleted);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
