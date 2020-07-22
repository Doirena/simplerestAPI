package com.dovile.simplerest.repositories;

import com.dovile.simplerest.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyRespository extends JpaRepository<PropertyEntity, Integer> {
    @Query(name = "PropertyEntity.findBYType")
    PropertyEntity findBYType(@Param("property") String property);
}
