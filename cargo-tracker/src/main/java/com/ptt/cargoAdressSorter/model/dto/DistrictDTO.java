package com.ptt.cargoAdressSorter.model.dto;

import com.ptt.cargoAdressSorter.model.entities.personEntity.PostmanEnt;
import lombok.Data;

@Data
public class DistrictDTO {
    private Long id;
    private String districtName;
    private String description;
    private PostmanEnt assignedPostman;


}
