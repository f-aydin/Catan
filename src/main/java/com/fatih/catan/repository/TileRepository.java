package com.fatih.catan.repository;

import com.fatih.catan.domain.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepository extends JpaRepository<Tile, Integer> {
}
