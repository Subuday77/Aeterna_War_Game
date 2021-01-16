package com.aeterna.game.beans;

import org.springframework.stereotype.Component;

@Component
public class Unit {
    private String id;
    private String name;
    private String side;
    private String currentCounty;
    private int hp;
    private int numberOfSteps;
    private int damage;
    private boolean isInUse;
    private boolean isSupport;

    public Unit() {
    }

    public Unit(String id, String name, String side, String currentCounty, int hp, int numberOfSteps, int damage, boolean isInUse, boolean isSupport) {
        this.id = id;
        this.name = name;
        this.side = side;
        this.currentCounty = currentCounty;
        this.hp = hp;
        this.numberOfSteps = numberOfSteps;
        this.damage = damage;
        this.isInUse = isInUse;
        this.isSupport = isSupport;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getCurrentCounty() {
        return currentCounty;
    }

    public void setCurrentCounty(String currentCounty) {
        this.currentCounty = currentCounty;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public boolean isSupport() {
        return isSupport;
    }

    public void setSupport(boolean support) {
        isSupport = support;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", side='" + side + '\'' +
                ", currentCounty='" + currentCounty + '\'' +
                ", hp=" + hp +
                ", numberOfSteps=" + numberOfSteps +
                ", damage=" + damage +
                ", isInUse=" + isInUse +
                ", isSupport=" + isSupport +
                '}';
    }
}
