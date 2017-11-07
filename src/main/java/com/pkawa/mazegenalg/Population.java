package com.pkawa.mazegenalg;

public class Population {

    private DNA[] population;

    public Population(double mutationRate, int popMax) {
        this.population = new DNA[popMax];
    }

    public DNA[] getPopulation() {
        return population;
    }
}
