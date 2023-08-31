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

import java.util.List;
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
    public ResponseEntity<CommonResponse> saveCargo(@RequestBody CargoDTO cargoDto) {
        ModelMapper modelMapper = new ModelMapper();
        CargoEnt savedCargo = null;
        try {
            CargoEnt cargoEnt = modelMapper.map(cargoDto, CargoEnt.class);
            savedCargo = cargoService.saveCargo(cargoEnt, cargoDto.getDistrictId());
            System.out.println("Cargo saved successfully.");
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(savedCargo).build());
        } catch (IllegalArgumentException e) {
            System.err.println("District with the given id not found.");
            return ResponseEntity.ok(CommonResponse.builder().message("District with the given id not found. Error : "+e.getMessage()).data(null).build()); // HTTP 404 Not Found
        } catch (Exception e) {
            System.err.println("An error occurred while saving cargo.");
            return ResponseEntity.ok(CommonResponse.builder().message("An error occurred while saving cargo. Error : "+e.getMessage()).data(null).build()); // HTTP 404 Not Found

        }
    }

    @DeleteMapping("deleteCargo")
    public ResponseEntity<CommonResponse> deleteCargo(@RequestBody CargoEnt cargoEnt) {
        try {
            cargoService.deleteCargo(cargoEnt);
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(true).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(false).build()); // HTTP 404 Not Found
        }
    }

    @PutMapping("updateCargo")
    public ResponseEntity<CommonResponse> updateCargo(@RequestBody CargoEnt cargoEnt) {
        CargoEnt updatedEnt = null;
        try {
            updatedEnt = cargoService.updateCargo(cargoEnt);
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(updatedEnt).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(null).build());
        }
    }

    @GetMapping("/cargo/{id}")
    public ResponseEntity<CommonResponse> getCargoById(@PathVariable Long id) {
        CargoEnt cargoEnt = null;
        try {
            cargoEnt = cargoService.getCargoById(id).get();
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(cargoEnt).build());
        } catch (Exception e) {
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(null).build());
        }

    }

    @GetMapping("/cargo/getAll")
    public ResponseEntity<CommonResponse> getCargoById() {
        List<CargoEnt> cargoEntList = null;
        try {
            cargoEntList = cargoService.getAllCargo();
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(cargoEntList).build());
        } catch (Exception e) {
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(null).build());
        }

    }

    @PostMapping("saveDistrict")
    public ResponseEntity<CommonResponse> saveDistrict(@RequestBody DistrictDTO districtDTO) {
        ModelMapper modelMapper = new ModelMapper();
        DistrictEnt savedDistrict = null;
        try {
            DistrictEnt districtEnt = modelMapper.map(districtDTO, DistrictEnt.class);
            savedDistrict = districtService.saveDistrict(districtEnt);
            System.out.println("District saved successfully.");
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(savedDistrict).build());
        } catch (Exception e) {
            System.err.println("An error occurred while saving district.");
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(null).build());
        }
    }


    @DeleteMapping("deleteDistrict")
    public ResponseEntity<CommonResponse> deleteDistrict(@RequestBody DistrictEnt districtEnt) {
        try {
            districtService.deleteDistrict(districtEnt);
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(true).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(false).build()); // HTTP 404 Not Found
        }
    }

    @PutMapping("updateDistrict")
    public ResponseEntity<CommonResponse> updateDistrinct(@RequestBody DistrictEnt districtEnt) {
        try {
            DistrictEnt updatedDistrict = districtService.updateDistrict(districtEnt);
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(updatedDistrict).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).build());
        }
    }

    @GetMapping("getDistrict/{id}")
    public ResponseEntity<CommonResponse> getDistrictById(@PathVariable Long id) {
        try {
            DistrictEnt districtEnt = districtService.getDistrictById(id).get();
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(districtEnt).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(null).build());
        }
    }

    @GetMapping("getAllDistrict")
    public ResponseEntity<CommonResponse> getAllDistrict() {
        try {
            List<DistrictEnt> districtEntList = districtService.getAllDistrict();
            return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(districtEntList).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful : "+e.getMessage()).data(null).build());
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
    public ResponseEntity<CommonResponse> updatePostman(@RequestBody PostmanEnt postmanEnt) {
        PostmanEnt updatedEnt = null;
        try {
            updatedEnt = postmanService.updatePostman(postmanEnt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(CommonResponse.builder().message(e.getMessage()).build());
        }
        if(updatedEnt == null)
            return ResponseEntity.ok(CommonResponse.builder().message("Not found.").data(null).build());
        else
           return ResponseEntity.ok(CommonResponse.builder().message("Successfull").data(updatedEnt).build());
    }


    @DeleteMapping("deletePostman")
    public ResponseEntity<CommonResponse> deletePostman(@RequestBody PostmanEnt postmanEnt) {
        try {
            postmanService.deletePostmanById(postmanEnt.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(CommonResponse.builder().message("Unsuccessful").data(false).build());
        }
        return ResponseEntity.ok(CommonResponse.builder().message("Successful").data(true).build());

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

    @GetMapping("getAllPostman")
    public ResponseEntity<CommonResponse> getAllPostman() {
        try {
            List<PostmanEnt> postmanEntList = postmanService.getAllPostman();
            return ResponseEntity.ok(CommonResponse.builder().data(postmanEntList).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(CommonResponse.builder().message(e.getMessage()).build());
        }
    }
}





