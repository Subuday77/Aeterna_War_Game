package com.aeterna.game.beans;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class County {
    private String id;
    private String owner;
    private boolean isCastle;
    private ArrayList<Unit> army;
    private ArrayList<String> neighbors;

    public County() {
    }

    public County(String id,  String owner, boolean isCastle, ArrayList<Unit> army, ArrayList<String> neighbors) {
        this.id = id;
        this.owner = owner;
        this.isCastle = isCastle;
        this.army = army;
        this.neighbors = neighbors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isCastle() {
        return isCastle;
    }

    public void setCastle(boolean castle) {
        isCastle = castle;
    }

    public ArrayList<Unit> getArmy() {
        return army;
    }

    public void setArmy(ArrayList<Unit> army) {
        this.army = army;
    }

    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<String> neighbors) {
        this.neighbors = neighbors;
    }

    public void setCanonicName() {
    }

    @Override
    public String toString() {
        return "County{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", isCastle=" + isCastle +
                ", army=" + army +
                ", neighbors=" + neighbors +
                '}';
    }
}
