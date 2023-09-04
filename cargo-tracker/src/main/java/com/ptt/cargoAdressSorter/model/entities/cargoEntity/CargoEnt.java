package com.ptt.cargoAdressSorter.model.entities.cargoEntity;

import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.model.entities.personEntity.PersonInfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class CargoEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "arrival_address")
    private String arrivalAddress;

    @Column(name = "exit_address")
    private String exitAddress;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_person_id", referencedColumnName = "id")
    private PersonInfo senderPerson;

    @OneToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "recipient_person_id", referencedColumnName = "id")
    private PersonInfo recipientPerson;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private DistrictEnt district;

    @Column(name = "last_updated_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date lastUpdatedTime;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "statusEnt", referencedColumnName = "id")
    private CargoStatusEnt statusEnt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
