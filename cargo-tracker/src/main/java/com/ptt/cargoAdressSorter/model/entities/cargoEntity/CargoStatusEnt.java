package com.ptt.cargoAdressSorter.model.entities.cargoEntity;

import com.ptt.cargoAdressSorter.enums.CargoStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
public class CargoStatusEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private CargoStatus status;
    private LocalDateTime transactionTime;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
