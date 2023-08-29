package com.ptt.cargoAdressSorter.model.dto;

import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.model.entities.personEntity.PersonInfo;
import lombok.Data;

import javax.persistence.*;
@Data
public class CargoDTO {
    private Long id;
    private String barcode;
    private String arrivalAddress;
    private String exitAddress;
    private PersonInfo senderPerson;
    private PersonInfo recipientPerson;
    private Long districtId;
}