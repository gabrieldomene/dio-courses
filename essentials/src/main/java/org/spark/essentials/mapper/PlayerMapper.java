package org.spark.essentials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.spark.essentials.domain.Player;
import org.spark.essentials.requests.PlayerPostRequestBody;
import org.spark.essentials.requests.PlayerPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class PlayerMapper {
    public static final PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    public abstract Player toPlayer(PlayerPostRequestBody playerPostRequestBody);

    public abstract Player toPlayer(PlayerPutRequestBody playerPutRequestBody);
}
