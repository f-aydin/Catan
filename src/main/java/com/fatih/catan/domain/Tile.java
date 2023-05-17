package com.fatih.catan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "building_on_tiles")
public class Tile {
    @Id
    @Column(name = "tileID")
    private final int tileID;

    @Column(name = "token")
    private final int token;

    @Column(name = "resourceType")
    private final Resource resource;

    public Tile(int tileID, int token, Resource resource) {
        this.tileID = tileID;
        this.token = token;
        this.resource = resource;
    }

    public int getTileID() {
        return tileID;
    }

    public int getToken() {
        return token;
    }

    public Resource getResource() {
        return resource;
    }

}
