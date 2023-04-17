package com.fatih.catan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/player1")

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

}
