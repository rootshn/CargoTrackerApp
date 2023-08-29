package com.ptt.cargoAdressSorter.model.entities.personEntity;

import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
public class PostmanEnt extends PersonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String registerNo;

}

