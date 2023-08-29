package com.ptt.cargoAdressSorter.repositories;

import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<CargoEnt,Long> {

}
