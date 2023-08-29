package com.ptt.cargoAdressSorter.model.entities.districtEntity;

import com.ptt.cargoAdressSorter.model.dto.DistrictDTO;
import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import com.ptt.cargoAdressSorter.model.entities.personEntity.PostmanEnt;
import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class DistrictEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="districtName")
    private String districtName;

    @Column(name ="description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postman_id", referencedColumnName = "id")
    private PostmanEnt assignedPostman;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void saveDistrict(DistrictEnt districtEnt, Class<DistrictDTO> districtDTOClass) {
    }
}