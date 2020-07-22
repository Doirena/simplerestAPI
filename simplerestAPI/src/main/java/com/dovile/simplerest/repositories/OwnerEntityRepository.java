package com.dovile.simplerest.repositories;

import com.dovile.simplerest.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerEntityRepository extends JpaRepository<OwnerEntity, Integer> {
}
