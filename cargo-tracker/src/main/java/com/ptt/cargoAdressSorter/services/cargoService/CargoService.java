package com.ptt.cargoAdressSorter.services.cargoService;

import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.repositories.CargoRepository;
import com.ptt.cargoAdressSorter.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    DistrictRepository districtRepository;

    public CargoEnt saveCargo(CargoEnt cargoEnt, Long districtId) {
        Optional<DistrictEnt> districtOptional = districtRepository.findById(districtId);

        if (districtOptional.isPresent()) {
            cargoEnt.setDistrict(districtOptional.get());
            return cargoRepository.save(cargoEnt);
        } else {
            throw new IllegalArgumentException("District with the given id not found");
        }
    }


    public void deleteCargoById(long id) {
        cargoRepository.deleteById(id);
    }

    public void deleteCargo(CargoEnt cargoEnt) {
        cargoRepository.delete(cargoEnt);
    }

    public void updateCargo(CargoEnt cargoEnt) {
        Optional<CargoEnt> existingCargoOptional = cargoRepository.findById(cargoEnt.getId());

        if (existingCargoOptional.isPresent()) {
            CargoEnt existingCargo = existingCargoOptional.get();

            existingCargo.setBarcode(cargoEnt.getBarcode());
            existingCargo.setArrivalAddress(cargoEnt.getArrivalAddress());
            existingCargo.setExitAddress(cargoEnt.getExitAddress());
            existingCargo.setSenderPerson(cargoEnt.getSenderPerson());
            existingCargo.setRecipientPerson(cargoEnt.getRecipientPerson());

            cargoRepository.save(existingCargo);
        } else {
            System.out.println("Böyle bir kayıt bulunamadı!");
        }
    }

    public Optional<CargoEnt> getCargoById(long id) {
        return cargoRepository.findById(id);
    }
}
