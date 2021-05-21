package com.marsrobot.app.domain;

import lombok.Data;

@Data
public class Terrain {

    private int westLimit = 0;
    private int eastLimit;
    private int northLimit;
    private int southLimit = 0;

    public Terrain(int eastLimit, int northLimit) {
        this.eastLimit = eastLimit;
        this.northLimit = northLimit;
    }
}
