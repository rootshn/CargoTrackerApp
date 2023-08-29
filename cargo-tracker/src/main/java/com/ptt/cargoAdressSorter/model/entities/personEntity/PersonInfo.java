package com.ptt.cargoAdressSorter.model.entities.personEntity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PersonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;
    private String personAddress;
    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;
    @Column(name = "identity_number")
    private String identityNumber;

}
