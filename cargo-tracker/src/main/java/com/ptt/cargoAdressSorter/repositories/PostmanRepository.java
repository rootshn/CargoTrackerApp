package com.ptt.cargoAdressSorter.repositories;

import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.model.entities.personEntity.PostmanEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostmanRepository extends JpaRepository<PostmanEnt, Long> {
}

