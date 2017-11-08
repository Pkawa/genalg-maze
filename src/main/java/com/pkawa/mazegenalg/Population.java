package com.pkawa.mazegenalg;

import java.util.ArrayList;
import java.util.Random;

public class Population {

    private DNA[] population;
    private double mutationRate;

    private int maxFitness = 0;
    private DNA bestResult;

    private ArrayList<DNA> matingPool = new ArrayList<DNA>();

    public Population(double mutationRate, int popMax) throws CloneNotSupportedException {
        this.population = new DNA[popMax];
        initializePopulation();
        this.mutationRate = mutationRate;
    }

    private void initializePopulation() throws CloneNotSupportedException {
        for(int i = 0; i < population.length; i++) {
            population[i] = new DNA();
        }
    }

    public Population(double mutationRate, DNA[] population) {
        this.population = population;
        this.mutationRate = mutationRate;
    }

    /**
     * Creates mating pool and performs natural selection
     */
    public void naturalSelection() {
        for (DNA dna: population) {
            if (dna.getFitnessScore() > maxFitness) {
                this.maxFitness = dna.getFitnessScore();
                this.bestResult = dna;
            }
        }

        for(DNA dna: population) {
            int fitness = (int) Math.round((double) dna.getFitnessScore() / maxFitness);
            int n = (int) Math.floor(fitness * 100);
            for (int i = 0; i < n; i++) {
                this.matingPool.add(dna);
            }
        }
    }

    public Population generateNewPopulation() throws CloneNotSupportedException {
        Random random = new Random();
        DNA[] newDNAPopulation = new DNA[population.length];
        for (int i = 0; i < population.length; i++) {
            int a = random.nextInt(matingPool.size());
            int b = random.nextInt(matingPool.size());
            DNA partnerA = matingPool.get(a);
            DNA partnerB = matingPool.get(b);
            DNA child = partnerA.sexyTime(partnerB);
            child.becomeXMan(mutationRate);
            newDNAPopulation[i] = child;
        }
        return new Population(mutationRate, newDNAPopulation);
    }

    public DNA getBestResult() {
        return bestResult;
    }

    public DNA[] getPopulation() {
        return population;
    }
}
