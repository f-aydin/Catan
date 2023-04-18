package com.fatih.catan.api;

import com.fatih.catan.domain.CatanModel;
import com.fatih.catan.domain.CatanPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api")

public class CatanController {
    private final CatanModel catanModel;

    @Autowired
    public CatanController(CatanModel catanModel) {
        this.catanModel = catanModel;
    }

    @GetMapping
    public List<CatanPerson> getPerson(){
        return catanModel.getPerson();
    }

    @PostMapping
    public void add(@RequestBody CatanModel.NewPersonRequest person){
        catanModel.savePerson(person);
    }

}
