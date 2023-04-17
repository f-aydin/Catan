package com.fatih.catan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatanRepository extends JpaRepository<CatanPerson, Integer> {
    List<CatanPerson> findByName(String name);
    List<CatanPerson> findByAge(int age);
}
