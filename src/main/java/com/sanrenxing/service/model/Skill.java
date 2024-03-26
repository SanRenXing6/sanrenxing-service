package com.sanrenxing.service.model;

public class Skill {

    private final String name;
    private final int rate;

    public Skill(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }
}
