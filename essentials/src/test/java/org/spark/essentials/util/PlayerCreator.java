package org.spark.essentials.util;

import org.spark.essentials.domain.Player;

public class PlayerCreator {

    public static Player createPlayerToBeSaved() {
        return Player.builder()
                .name("Gabriel")
                .build();
    }

    public static Player createValidPlayer() {
        return Player.builder()
                .id(1L)
                .name("Gabriel")
                .build();
    }

    public static Player createValidUpdatedPlayer() {
        return Player.builder()
                .id(1L)
                .name("João")
                .build();
    }
}
