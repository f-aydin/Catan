package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.stream.Collector;

@Entity
public class Tile {
    @Id
    private int tileID;
    private int token;
    private Resource resourceType;
    private boolean hasRobber;

    public Tile() {
    }

    public Tile(int tileID, int token, Resource resourceType) {
        this.tileID = tileID;
        this.token = token;
        this.resourceType = resourceType;
    }

    public int getTileID() {
        return tileID;
    }

    public int getToken() {
        return token;
    }

    public Resource getResourceType() {
        return resourceType;
    }

    public boolean getHasRobber() {
        return hasRobber;
    }

    public void setHasRobber(boolean hasRobber) {
        this.hasRobber = hasRobber;
    }

    @Override
    public boolean equals(Object tile){
        if(tile == this){
            return true;
        }

        if(!(tile instanceof Tile t)){
            return false;
        }

        return tileID == t.tileID &&
                token == t.token &&
                resourceType.equals(t.resourceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tileID, token, resourceType);
    }
}
