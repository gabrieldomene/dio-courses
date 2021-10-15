package org.spark.essentials.util;

import org.spark.essentials.requests.PlayerPostRequestBody;
import org.spark.essentials.requests.PlayerPutRequestBody;

public class PlayerPutRequestBodyCreator {
    public static PlayerPutRequestBody createPlayerPutRequestBody() {
        return PlayerPutRequestBody.builder()
                .id(PlayerCreator.createValidUpdatedPlayer().getId())
                .name(PlayerCreator.createValidUpdatedPlayer().getName())
                .build();
    }
}
