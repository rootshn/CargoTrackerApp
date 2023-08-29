package com.ptt.cargoAdressSorter.services.postmanService;

import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import com.ptt.cargoAdressSorter.model.entities.districtEntity.DistrictEnt;
import com.ptt.cargoAdressSorter.model.entities.personEntity.PostmanEnt;
import com.ptt.cargoAdressSorter.repositories.DistrictRepository;
import com.ptt.cargoAdressSorter.repositories.PostmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.Id;

@Service
public class PostmanService {



    private PostmanRepository postmanRepository;

    private DistrictRepository districtRepository;


    public PostmanService(PostmanRepository postmanRepository, DistrictRepository districtRepository) {
        this.postmanRepository = postmanRepository;
        this.districtRepository = districtRepository;
    }

    public void savePostmanWithAssignedDistrict(PostmanEnt postmanEnt, DistrictEnt districtEnt) {
        postmanRepository.save(postmanEnt);
    }


    @Autowired
    public PostmanService(PostmanRepository postmanRepository) {
        this.postmanRepository = postmanRepository;
    }

    public PostmanEnt savePostman(PostmanEnt postmanEnt) {
        PostmanEnt responsEnt = postmanRepository.save(postmanEnt);
        return responsEnt;
    }

    public Boolean updatePostman(PostmanEnt updatedPostman) throws Exception {

        Long postId = updatedPostman.getId();
        PostmanEnt existingPostman = postmanRepository.findById(postId).orElse(null);

        if (existingPostman != null) {
            postmanRepository.save(updatedPostman);
        } else {
            return false;
        }
        return true;
    }


//    public void updatePostman(PostmanEnt postmanEnt) {
//        PostmanEnt existingPostman = postmanRepository.findById(postmanEnt.getId()).orElse(null);
//
//        if (existingPostman != null) {
//
//            postmanRepository.save(new PostmanEnt());
//
//        } else {
//            System.out.println("Böyle bir kayıt bulunamadı!");
//        }
//    }

    public void deletePostmanById(long id) {
        postmanRepository.deleteById(id);
    }

    public PostmanEnt getPostmanById(Long id){
        return postmanRepository.findById(id).orElse(null);
    }
}

