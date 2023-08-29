package com.ptt.cargoAdressSorter.repositories;

import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository <DistrictEnt, Long> {
}
