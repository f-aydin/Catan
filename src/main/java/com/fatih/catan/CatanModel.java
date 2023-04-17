package com.fatih.catan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
