/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class Revenue {
    private int day;
    private double budget;
    private float winRate;

    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    public float getWinRate() {
        return winRate;
    }

    public Revenue(double budget, float winRate) {
        this.budget = budget;
        this.winRate = winRate;
    }
    public Revenue(double budget) {
        this.budget = budget;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void addBudget(double budget) {
        this.budget += budget;
    }

    public int getDay() {
        return day;
    }

    public double getBudget() {
        return budget;
    }
    
}
