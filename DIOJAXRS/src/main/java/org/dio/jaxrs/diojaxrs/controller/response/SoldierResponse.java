package org.dio.jaxrs.diojaxrs.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.dio.jaxrs.diojaxrs.dto.Soldier;
import org.dio.jaxrs.diojaxrs.entity.SoldierEntity;
import org.springframework.hateoas.RepresentationModel;

public class SoldierResponse extends RepresentationModel<SoldierResponse> {

    private Long id;
    private String cpf;
    private String name;
    private String race;
    private String weapon;
    private String status;

    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
