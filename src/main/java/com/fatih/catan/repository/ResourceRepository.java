package com.fatih.catan.repository;

import com.fatih.catan.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository <Resource, Integer> {

}
