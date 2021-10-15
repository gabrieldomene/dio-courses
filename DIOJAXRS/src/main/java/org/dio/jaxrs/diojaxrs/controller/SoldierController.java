package org.dio.jaxrs.diojaxrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dio.jaxrs.diojaxrs.controller.request.SoldierEditRequest;
import org.dio.jaxrs.diojaxrs.controller.response.SoldierListResponse;
import org.dio.jaxrs.diojaxrs.controller.response.SoldierResponse;
import org.dio.jaxrs.diojaxrs.dto.Soldier;
import org.dio.jaxrs.diojaxrs.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/soldier")
public class SoldierController {

    private final SoldierService soldierService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SoldierController(SoldierService soldierService, ObjectMapper objectMapper) {
        this.soldierService = soldierService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoldierResponse> searchSoldier(@PathVariable Long id) {
        SoldierResponse soldierResponse = soldierService.searchSoldier(id);
        return ResponseEntity.status(HttpStatus.OK).body(soldierResponse);
    }

    @GetMapping
    public ResponseEntity<List<SoldierResponse>> listSoldiers() {
        List<SoldierResponse> soldiers = soldierService.listSoldiers().stream()
                .map(it -> objectMapper.convertValue(it, SoldierResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(soldiers);
    }

    @PostMapping
    public ResponseEntity createSoldier(@RequestBody Soldier soldier) {
        soldierService.createSoldier(soldier);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSoldier(@PathVariable Long id,
                                                 @RequestBody SoldierEditRequest soldierEditRequest) {
        soldierService.updateSoldier(id, soldierEditRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoldier(@PathVariable Long id) {
        soldierService.deleteSoldier(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/front-castle/{id}")
    public ResponseEntity<Void> frontCastle(@PathVariable Long id) {
//        do something
        return ResponseEntity.ok().build();
    }

}
