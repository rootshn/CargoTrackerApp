package com.ptt.cargoAdressSorter.services.districtService;

import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictService {
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public void saveDistrict(DistrictEnt districtEnt) {
        districtRepository.save(districtEnt);
    }

    public void deleteDistrictById(long id) {
        districtRepository.deleteById(id);
    }

    public void deleteDistrict(DistrictEnt districtEnt) {
        if (districtEnt != null) {
            districtRepository.delete(districtEnt);
        } else {
            System.out.println("District object is null.");
        }
    }

    public void updateDistrict(DistrictEnt districtEnt) {
        if (districtEnt.getId() != null) {
            Optional<DistrictEnt> existingDistrictOptional = districtRepository.findById(districtEnt.getId());

            if (existingDistrictOptional.isPresent()) {
                DistrictEnt existingDistrict = existingDistrictOptional.get();
                existingDistrict.setDistrictName(districtEnt.getDistrictName());
                existingDistrict.setDescription(districtEnt.getDescription());

                districtRepository.save(existingDistrict);
            } else {
                System.out.println("There is no district with the provided ID.");
            }
        } else {
            System.out.println("District ID is null.");
        }
    }
    public Optional<DistrictEnt> getDistrictById(long id) {
        return districtRepository.findById(id);
    }
}
