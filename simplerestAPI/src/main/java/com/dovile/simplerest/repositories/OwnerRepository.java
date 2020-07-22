package com.dovile.simplerest.repositories;

import com.dovile.simplerest.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

    @Query(name = "OwnerEntity.findBYName")
    OwnerEntity findBYName (@Param("owner") String owner);
}
