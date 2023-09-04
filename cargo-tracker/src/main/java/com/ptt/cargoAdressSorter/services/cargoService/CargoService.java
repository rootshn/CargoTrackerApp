package com.ptt.cargoAdressSorter.services.cargoService;

import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.repositories.CargoRepository;
import com.ptt.cargoAdressSorter.repositories.DistrictRepository;
import com.ptt.cargoAdressSorter.utils.CargoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ptt.cargoAdressSorter.algorithm.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    DistrictRepository districtRepository;

    public List<CargoEnt> doSortingCargo(List<CargoEnt> unSortCargoList) throws ParseException {
        MultiRelativeSorting multiRelativeSorting = new MultiRelativeSorting();
        List<CargoEnt> cargoEntList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        multiRelativeSorting.clearStore();
        int count = 0; // son 30 günün gönderileri ile karşılaştıracak.
        do {
            count++;
            Date endDate = dateFormat.parse(dateFormat.format(cal.getTime()));
            cal.add(Calendar.DATE, -1);
            Date startDate = dateFormat.parse(dateFormat.format(cal.getTime()));
            cargoEntList = cargoRepository.findByLastUpdatedTimeBetweenOrderByLastUpdatedTimeAsc(startDate, endDate);
            if(cargoEntList.size()>0){
                LinkedHashMap<Integer,CargoEnt> dailyPostedCargos = new LinkedHashMap<>();
                for (int i=0;i<cargoEntList.size();i++) {
                    dailyPostedCargos.put(i,cargoEntList.get(i));
                }
                multiRelativeSorting.addToStore(dailyPostedCargos);
            }

        }while(count<30); // son 30 günün gönderileri ile karşılaştıracak.


        return  CargoUtils.linkedHashMapToArrayList(multiRelativeSorting.doRelativeSort(CargoUtils.arrayListToLinkedHashMap(unSortCargoList)));
    }

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

    public CargoEnt updateCargo(CargoEnt cargoEnt) {
        Optional<CargoEnt> existingCargoOptional = cargoRepository.findById(cargoEnt.getId());
        CargoEnt updatedEnt = null;
        if (existingCargoOptional.isPresent()) {
            CargoEnt existingCargo = existingCargoOptional.get();

            existingCargo.setBarcode(cargoEnt.getBarcode());
            existingCargo.setArrivalAddress(cargoEnt.getArrivalAddress());
            existingCargo.setExitAddress(cargoEnt.getExitAddress());
            existingCargo.setSenderPerson(cargoEnt.getSenderPerson());
            existingCargo.setRecipientPerson(cargoEnt.getRecipientPerson());

            updatedEnt = cargoRepository.save(existingCargo);
        } else {
            System.out.println("Böyle bir kayıt bulunamadı!");
        }
        return cargoEnt;
    }

    public Optional<CargoEnt> getCargoById(long id) {
        return cargoRepository.findById(id);
    }

    public List<CargoEnt> getAllCargo() {
        return cargoRepository.findAll();
    }
}
