package com.fatih.catan.domain;

import java.util.Random;

public enum Resource {
    LUMBER,
    ORE,
    GRAIN,
    BRICK,
    WOOL,
    NULL;

    private static final Random random = new Random();

    public static Resource randomResource(){
        Resource[] resources = values();
        return resources[random.nextInt(5)];
    }
}
