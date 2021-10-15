package org.spark.essentials.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spark.essentials.domain.Player;
import org.spark.essentials.requests.PlayerPostRequestBody;
import org.spark.essentials.requests.PlayerPutRequestBody;
import org.spark.essentials.service.PlayerService;
import org.spark.essentials.util.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("player")
@Log4j2
@RequiredArgsConstructor
public class PlayerController {
    private final DateUtil dateUtil = new DateUtil();
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<Page<Player>> list(Pageable pageable) {
//        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(playerService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Player>> listAll() {
//        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(playerService.listAllNonPageable());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> findById(@PathVariable long id) {
        return ResponseEntity.ok(playerService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(value = "/find")
    public ResponseEntity<List<Player>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(playerService.findByName(name));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Player> saveNewPlayer(@RequestBody @Valid PlayerPostRequestBody playerPostRequestBody) {
        return new ResponseEntity<>(playerService.save(playerPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody PlayerPutRequestBody playerPutRequestBody) {
        playerService.replace(playerPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
