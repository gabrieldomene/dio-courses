package org.dio.jaxrs.diojaxrs.repository.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dio.jaxrs.diojaxrs.controller.SoldierController;
import org.dio.jaxrs.diojaxrs.controller.response.SoldierListResponse;
import org.dio.jaxrs.diojaxrs.controller.response.SoldierResponse;
import org.dio.jaxrs.diojaxrs.entity.SoldierEntity;
import org.dio.jaxrs.diojaxrs.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SoldierResource {

    private final ObjectMapper objectMapper;

    @Autowired
    public SoldierResource(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SoldierListResponse createLink(SoldierEntity soldierEntity) {
        SoldierListResponse soldierListResponse = objectMapper.convertValue(soldierEntity, SoldierListResponse.class);
        Link link =
                linkTo(methodOn(SoldierService.class).searchSoldier(soldierEntity.getId())).withSelfRel();
        soldierListResponse.add(link);
        return soldierListResponse;
    }

    public SoldierResponse createLinkDetails(SoldierEntity soldierEntity) {
        SoldierResponse soldierListResponse = objectMapper.convertValue(soldierEntity,
                SoldierResponse.class);
        if (soldierEntity.getStatus().equals("dead")) {
            Link link =
                    linkTo(methodOn(SoldierController.class).deleteSoldier(soldierEntity.getId()))
                            .withRel("delete")
                            .withType("delete")
                            .withTitle("Delete soldier");
            soldierListResponse.add(link);
        } else if (soldierEntity.getStatus().equals("live")) {
            Link link = linkTo(methodOn(SoldierController.class).frontCastle(soldierEntity.getId()))
                    .withRel("battle")
                    .withTitle("Go to the front")
                    .withType("put");
            soldierListResponse.add(link);
        }
        return soldierListResponse;
    }
}
