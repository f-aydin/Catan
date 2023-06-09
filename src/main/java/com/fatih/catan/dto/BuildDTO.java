package com.fatih.catan.dto;

import com.fatih.catan.domain.BuildingType;

public class BuildDTO {
    private Integer playerID;
    private Integer tile1;
    private Integer tile2;
    private Integer tile3;
    private BuildingType type;

    public Integer getPlayerID() {
        return playerID;
    }

    public Integer getTile1() {
        return tile1;
    }

    public Integer getTile2() {
        return tile2;
    }

    public Integer getTile3() {
        return tile3;
    }

    public BuildingType getType(){ return type;}
}
