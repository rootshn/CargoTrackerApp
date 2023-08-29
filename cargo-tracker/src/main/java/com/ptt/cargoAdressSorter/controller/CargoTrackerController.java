package com.ptt.cargoAdressSorter.controller;

import com.ptt.cargoAdressSorter.model.CommonResponse;
import com.ptt.cargoAdressSorter.model.dto.CargoDTO;
import com.ptt.cargoAdressSorter.model.dto.DistrictDTO;
import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.model.entities.personEntity.PostmanEnt;
import com.ptt.cargoAdressSorter.services.cargoService.CargoService;
import com.ptt.cargoAdressSorter.services.districtService.DistrictService;
import com.ptt.cargoAdressSorter.services.postmanService.PostmanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cargoTracker")
public class CargoTrackerController {
    @Autowired
    private PostmanService postmanService;

    @Autowired
    private CargoService cargoService;

    private DistrictService districtService;

    @Autowired
    public CargoTrackerController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("saveCargo")
    public ResponseEntity<String> saveCargo(@RequestBody CargoDTO cargoDto) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            CargoEnt cargoEnt = modelMapper.map(cargoDto, CargoEnt.class);
            cargoService.saveCargo(cargoEnt, cargoDto.getDistrictId());
            System.out.println("Cargo saved successfully.");
            return ResponseEntity.ok("Successful");
        } catch (IllegalArgumentException e) {
            System.err.println("District with the given id not found.");
            return ResponseEntity.ok("District with the given id not found");
        } catch (Exception e) {
            System.err.println("An error occurred while saving cargo.");
            return ResponseEntity.ok("Unsuccessful");
        }
    }

    @DeleteMapping("deleteCargo")
    public ResponseEntity<String> deleteCargo(@RequestBody CargoEnt cargoEnt) {
        try {
            cargoService.deleteCargo(cargoEnt);
            return ResponseEntity.ok("Successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    @PutMapping("updateCargo")
    public ResponseEntity<String> updateCargo(@RequestBody CargoEnt cargoEnt) {
        try {
            cargoService.updateCargo(cargoEnt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Successful");
        }
        return ResponseEntity.ok("Unsuccessful");
    }

    @GetMapping("/cargo/{id}")
    public Optional<CargoEnt> getCargoById(@PathVariable Long id) {
        return cargoService.getCargoById(id);

    }

    @PostMapping("saveDistrict")
    public ResponseEntity<String> saveDistrict(@RequestBody DistrictDTO districtDTO) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            DistrictEnt districtEnt = modelMapper.map(districtDTO, DistrictEnt.class);
            districtService.saveDistrict(districtEnt);
            System.out.println("District saved successfully.");
            return ResponseEntity.ok("Successful");
        } catch (Exception e) {
            System.err.println("An error occurred while saving district.");
            return ResponseEntity.ok("Unsuccessful");
        }
    }


    @DeleteMapping("deleteDistrict")
    public ResponseEntity<String> deleteDistrict(@RequestBody DistrictEnt districtEnt) {
        try {
            districtService.deleteDistrict(districtEnt);
            return ResponseEntity.ok("Successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    @PutMapping("updateDistrict")
    public ResponseEntity<String> updateDistrinct(@RequestBody DistrictEnt districtEnt) {
        try {
            districtService.updateDistrict(districtEnt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Unsuccessful");
        }
        return ResponseEntity.ok("Successful");
    }

    @GetMapping("getDistrict/{id}")
    public ResponseEntity<String> getDistrictById(@PathVariable Long id) {
        try {
            districtService.getDistrictById(id);
            return ResponseEntity.ok("Successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Unsuccessful");
        }
    }

    @PostMapping("/savePostman")
    public ResponseEntity<CommonResponse> savePostman(@RequestBody PostmanEnt postmanEnt) {
        PostmanEnt savedEnt = null;
        try {
            savedEnt = postmanService.savePostman(postmanEnt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(CommonResponse.builder().message(e.getMessage()).build());
        }
        return ResponseEntity.ok(CommonResponse.builder().data(savedEnt).build());
    }

    @PutMapping("/updatePostman")
    public ResponseEntity<String> updatePostman(@RequestBody PostmanEnt postmanEnt) {
        Boolean response = false;
        try {
            response = postmanService.updatePostman(postmanEnt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        if(!response)
            return ResponseEntity.notFound().build();
        else
           return ResponseEntity.ok("Successfull");
    }


    @DeleteMapping("deletePostman")
    public ResponseEntity<String> deletePostman(@RequestBody PostmanEnt postmanEnt) {
        try {
            postmanService.deletePostmanById(postmanEnt.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Unsuccessful");
        }
        return ResponseEntity.ok("Successful");

    }

    @GetMapping("getPostman/{id}")
    public ResponseEntity<CommonResponse> getPostman(@PathVariable Long id) {
        try {
            PostmanEnt postmanEnt = postmanService.getPostmanById(id);
            return ResponseEntity.ok(CommonResponse.builder().data(postmanEnt).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(CommonResponse.builder().message(e.getMessage()).build());
        }
    }
}





