package com.pkawa.mazegenalg;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Labirynth.printMap();
        runGeneticAlgorithm(2000, 0.01, 1000);

    }

    public static void runGeneticAlgorithm(int numberOfIterations, double mutationRate, int maxPopulation) {
        try {
            Population population = new Population(mutationRate, maxPopulation);

            for (int i = 0; i < numberOfIterations; i++) {
                try {
                    population.naturalSelection();
                    printData(population, i);
                    population = population.generateNewPopulation();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
        }
    }

    private static void printData(Population population, int i) throws CloneNotSupportedException {
        System.out.println("Generation " + (i + 1) + " best fitness score: " + population.getBestResult().getFitnessScore());
        drawResultOverTheMap(population.getBestResult().getMoveSet());
        System.out.println("Winning move set: " + Arrays.toString(population.getBestResult().getMoveSet()));
        System.out.println("==========================");
    }

    private static void drawResultOverTheMap(Move[] moveSet) throws CloneNotSupportedException {
        String pathSign = "▨";
        Position currentPosition = Labirynth.STARTING_POSITION.clone();
        String[][] drawnMap = Labirynth.drawMap();

        drawnMap[currentPosition.getRow()][currentPosition.getColumn()] = pathSign;
        for (Move move: moveSet) {
            switch (move) {
                case RIGHT:
                    if (currentPosition.canMoveRight()) {
                        currentPosition.moveRight();
                    }
                    break;
                case DOWN:
                    if (currentPosition.canMoveDown())  {
                        currentPosition.moveDown();
                    }
                    break;
                case UP:
                    if (currentPosition.canMoveUp()) currentPosition.moveUp();
                    break;
                case LEFT:
                    if (currentPosition.canMoveLeft()) currentPosition.moveLeft();
                    break;
                default:
                    throw new IllegalArgumentException("Can't move in any direction");
            }
            drawnMap[currentPosition.getRow()][currentPosition.getColumn()] = pathSign;
        }
        Labirynth.printMap(drawnMap);
    }
}
