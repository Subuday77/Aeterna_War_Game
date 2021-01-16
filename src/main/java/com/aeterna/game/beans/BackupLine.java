package com.aeterna.game.beans;

import org.springframework.stereotype.Component;

@Component
public class BackupLine {
    private String turn;
    private int stepNumber;
    GlobalMap globalMap;

    public BackupLine() {
    }

    public BackupLine(String turn, int stepNumber, GlobalMap globalMap) {
        this.turn = turn;
        this.stepNumber = stepNumber;
        this.globalMap = globalMap;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public GlobalMap getGlobalMap() {
        return globalMap;
    }

    public void setGlobalMap(GlobalMap globalMap) {
        this.globalMap = globalMap;
    }

    @Override
    public String toString() {
        return "BackupLine{" +
                "turn='" + turn + '\'' +
                ", stepNumber=" + stepNumber +
                ", globalMap=" + globalMap +
                '}';
    }
}
