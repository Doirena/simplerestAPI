package com.dovile.simplerest.repositories;

import com.dovile.simplerest.entities.BuildingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRecordRepository extends JpaRepository<BuildingRecordEntity,Integer> {
    @Query(name = "BuildingRecordEntity.RealEstateTaxes")
    String RealEstateTaxes (@Param("id") Integer id);
}
