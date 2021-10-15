package org.dio.jaxrs.diojaxrs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dio.jaxrs.diojaxrs.controller.request.SoldierEditRequest;
import org.dio.jaxrs.diojaxrs.controller.response.SoldierListResponse;
import org.dio.jaxrs.diojaxrs.controller.response.SoldierResponse;
import org.dio.jaxrs.diojaxrs.dto.Soldier;
import org.dio.jaxrs.diojaxrs.entity.SoldierEntity;
import org.dio.jaxrs.diojaxrs.repository.SoldierRepository;
import org.dio.jaxrs.diojaxrs.repository.resource.SoldierResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoldierService {

    private final SoldierRepository soldierRepository;
    private final ObjectMapper objectMapper;
    private final SoldierResource soldierResource;

    @Autowired
    public SoldierService(SoldierRepository soldierRepository, ObjectMapper objectMapper, SoldierResource soldierResource) {
        this.soldierRepository = soldierRepository;
        this.objectMapper = objectMapper;
        this.soldierResource = soldierResource;
    }

    public SoldierResponse searchSoldier(Long id) {
        SoldierEntity soldierEntity = soldierRepository.findById(id).orElseThrow();
        return objectMapper.convertValue(soldierEntity, SoldierResponse.class);
    }

    public void createSoldier(Soldier soldier) {
        SoldierEntity soldierEntity = objectMapper.convertValue(soldier,
                SoldierEntity.class);
        soldierRepository.save(soldierEntity);
    }

    public void updateSoldier(Long id, SoldierEditRequest soldierEditRequest) {
        SoldierEntity soldierEntity = objectMapper.convertValue(soldierEditRequest,
                SoldierEntity.class);
        soldierEntity.setId(id);
        soldierRepository.save(soldierEntity);
    }

    public void deleteSoldier(Long id) {
        SoldierEntity soldier = soldierRepository.findById(id).orElseThrow();
        soldierRepository.delete(soldier);
    }

    public List<SoldierListResponse> listSoldiers() {
        List<SoldierEntity> allSoldiers = soldierRepository.findAll();
        return allSoldiers.stream()
                .map(soldierResource::createLink).collect(Collectors.toList());
    }
}
