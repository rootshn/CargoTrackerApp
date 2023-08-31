package com.ptt.cargoAdressSorter.services.districtService;

import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public DistrictEnt saveDistrict(DistrictEnt districtEnt) {
        return districtRepository.save(districtEnt);
    }

    public void deleteDistrictById(long id) {
        districtRepository.deleteById(id);
    }

    public void deleteDistrict(DistrictEnt districtEnt) {
            districtRepository.delete(districtEnt);
    }

    public DistrictEnt updateDistrict(DistrictEnt districtEnt) {
        DistrictEnt updatedEnt = null;
        if (districtEnt.getId() != null) {
            Optional<DistrictEnt> existingDistrictOptional = districtRepository.findById(districtEnt.getId());

            if (existingDistrictOptional.isPresent()) {
                DistrictEnt existingDistrict = existingDistrictOptional.get();
                existingDistrict.setDistrictName(districtEnt.getDistrictName());
                existingDistrict.setDescription(districtEnt.getDescription());
                updatedEnt = districtRepository.save(existingDistrict);
            } else {
                System.out.println("There is no district with the provided ID.");
            }
        } else {
            System.out.println("District ID is null.");
        }
        return updatedEnt;
    }
    public Optional<DistrictEnt> getDistrictById(long id) {
        return districtRepository.findById(id);
    }

    public List<DistrictEnt> getAllDistrict() {
        return districtRepository.findAll();
    }
}
