package com.ptt.cargoAdressSorter.repositories;

import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<CargoEnt,Long> {
    List<CargoEnt> findByLastUpdatedTimeBetweenOrderByLastUpdatedTimeAsc(Date startDate,Date endDate);

}
