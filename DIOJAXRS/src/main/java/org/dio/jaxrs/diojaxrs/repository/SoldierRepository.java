package org.dio.jaxrs.diojaxrs.repository;

import org.dio.jaxrs.diojaxrs.entity.SoldierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldierRepository extends JpaRepository<SoldierEntity, Long> {

}
