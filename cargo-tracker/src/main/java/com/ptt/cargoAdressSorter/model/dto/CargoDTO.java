package com.ptt.cargoAdressSorter.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.model.entities.personEntity.PersonInfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class CargoDTO {
    private Long id;
    private String barcode;
    private String arrivalAddress;
    private String exitAddress;
    private PersonInfo senderPerson;
    private PersonInfo recipientPerson;
    private Long districtId;
    private String lastUpdatedTimeStr;
}