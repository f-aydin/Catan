package com.fatih.catan.repository;

import com.fatih.catan.domain.CatanPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatanRepository extends JpaRepository<CatanPerson, Integer> {
    List<CatanPerson> findByFirstName(String firstname);
    List<CatanPerson> findByAge(int age);
}
