package org.spark.essentials.service;

import lombok.RequiredArgsConstructor;
import org.spark.essentials.domain.Player;
import org.spark.essentials.exception.BadRequestException;
import org.spark.essentials.mapper.PlayerMapper;
import org.spark.essentials.repository.PlayerRepository;
import org.spark.essentials.requests.PlayerPostRequestBody;
import org.spark.essentials.requests.PlayerPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    public Page<Player> listAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    public List<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }

    public List<Player> listAllNonPageable() {
        return playerRepository.findAll();
    }


    public Player findByIdOrThrowBadRequestException(long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Player ID not found"));
    }

    @Transactional
    public Player save(PlayerPostRequestBody playerPostRequestBody) {
        Player player = PlayerMapper.INSTANCE.toPlayer(playerPostRequestBody);
        return playerRepository.save(player);
    }

    public void delete(long id) {
        playerRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(PlayerPutRequestBody playerPutRequestBody) {
        Player savedPlayer = findByIdOrThrowBadRequestException(playerPutRequestBody.getId());
        Player player = PlayerMapper.INSTANCE.toPlayer(playerPutRequestBody);
        playerRepository.save(player);
    }
}
