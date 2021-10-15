package org.spark.essentials.util;

import org.spark.essentials.domain.Player;
import org.spark.essentials.requests.PlayerPostRequestBody;

public class PlayerPostRequestBodyCreator {
    public static PlayerPostRequestBody createPlayerPostRequestBody() {
        return PlayerPostRequestBody.builder()
                .name(PlayerCreator.createPlayerToBeSaved().getName())
                .build();
    }
}
