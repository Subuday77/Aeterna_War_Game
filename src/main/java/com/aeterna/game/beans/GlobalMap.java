package com.aeterna.game.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GlobalMap {

    private ArrayList<County> counties;

    public GlobalMap() {
    }

    public GlobalMap(ArrayList<County> counties) {
        this.counties = counties;
    }

    public ArrayList<County> getCounties() {
        return counties;
    }

    public void setCounties(ArrayList<County> counties) {
        this.counties = counties;
    }

    @Override
    public String toString() {
        return "globalMap{" +
                "counties=" + counties +
                '}';
    }
}
