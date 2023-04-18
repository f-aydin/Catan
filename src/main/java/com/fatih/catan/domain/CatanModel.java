package com.fatih.catan.domain;

import com.fatih.catan.repository.CatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CatanModel {
    private final CatanRepository catanRepository;

    @Autowired
    public CatanModel(CatanRepository catanRepository) {
        this.catanRepository = catanRepository;
    }

    public List<CatanPerson> getPerson(){
        return catanRepository.findAll();
    }

    public record NewPersonRequest(
            int age,
            String firstname
    ){}

    public void savePerson(@RequestBody NewPersonRequest request){
        CatanPerson person = new CatanPerson();
        person.setAge(request.age());
        person.setFirstName(request.firstname());
        catanRepository.save(person);
    }

}
