package com.pkawa.mazegenalg;

import java.util.Random;

public class DNA {

    private Move[] moveSet = new Move[40];
    private Position finalPosition;

    private int fitnessScore = Integer.MIN_VALUE;

    public DNA() throws CloneNotSupportedException {
        generateNewDNA();
        runDNA();
    }

    public DNA(Move[] moveSet) throws CloneNotSupportedException {
        this.moveSet = moveSet;
        runDNA();
    }

    private void runDNA() throws CloneNotSupportedException {
        calculateFitness();
    }

    private void generateNewDNA() {
        for(int i = 0; i < moveSet.length; i++) {
            Move result = getRandomMove();
            moveSet[i] = result;
        }
    }

    private Move getRandomMove() {
        Move result;
        Random random = new Random();
        int randomNumber = random.nextInt(Move.values().length);
        switch (randomNumber) {
            case 0:
                result = Move.LEFT;
                break;
            case 1:
                result = Move.RIGHT;
                break;
            case 2:
                result = Move.UP;
                break;
            case 3:
                result = Move.DOWN;
                break;
            default:
                result = Move.RIGHT;
                break;
        }
        return result;
    }

    private Position walkToTheTarget() throws CloneNotSupportedException {
        Position currentDNAPosition = new Position();
        for (Move move: moveSet) {
            switch (move) {
                case RIGHT:
                    if (currentDNAPosition.canMoveRight()) {
                        currentDNAPosition.moveRight();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                case DOWN:
                    if (currentDNAPosition.canMoveDown()) {
                        currentDNAPosition.moveDown();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                case UP:
                    if (currentDNAPosition.canMoveUp()) {
                        currentDNAPosition.moveUp();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                case LEFT:
                    if (currentDNAPosition.canMoveLeft()) {
                        currentDNAPosition.moveLeft();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Can't move in any direction");
            }
        }
        finalPosition = currentDNAPosition;
        return currentDNAPosition;
    }

    private void calculateFitness() throws CloneNotSupportedException {
        walkToTheTarget();
        int resultAchievedScore =
                (finalPosition.getColumn() == Labirynth.ENDING_POSITION.getColumn()
                        && finalPosition.getRow() == Labirynth.ENDING_POSITION.getRow())
                        ? 200 :
                        (finalPosition.getColumn() + finalPosition.getRow());
        int leftStepsScore = finalPosition.getLeftSteps() / 2;
        int howCloseToEndScore = (finalPosition.getColumn() + finalPosition.getRow()
                - Labirynth.ENDING_POSITION.getRow() - Labirynth.ENDING_POSITION.getColumn());

        this.fitnessScore = resultAchievedScore + leftStepsScore + howCloseToEndScore + calculateFitnessSupport();
    }

    private int calculateFitnessSupport() throws CloneNotSupportedException {
        int fitnessScore = 1;
        Position currentDNAPosition = new Position();
        for (Move move : moveSet) {
            if (currentDNAPosition.getColumn() == 11 && currentDNAPosition.getRow() == 11) return 200;
            switch (move) {
                case RIGHT:
                    if (currentDNAPosition.canMoveRight()) {
                        currentDNAPosition.moveRight();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                case DOWN:
                    if (currentDNAPosition.canMoveDown()) {
                        currentDNAPosition.moveDown();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                case UP:
                    if (currentDNAPosition.canMoveUp()) {
                        currentDNAPosition.moveUp();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                case LEFT:
                    if (currentDNAPosition.canMoveLeft()) {
                        currentDNAPosition.moveLeft();
                        fitnessScore = increaseFitness(fitnessScore);
                    } else {
                        fitnessScore = decreaseFitness(fitnessScore);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Can't move in any direction");
            }
        }
        return fitnessScore;
    }

    private int decreaseFitness(int score) {
        return score - 1;
    }

    private int increaseFitness(int score) {
        return score + 1;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public Move[] getMoveSet() {
        return moveSet;
    }

    public void setMoveSet(Move[] moveSet) {
        this.moveSet = moveSet;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

    /**
     * crossover/reproduction method
     * @param partnerB partner for reproduction purposes
     * @return combined DNA from both partners
     */
    public DNA sexyTime(DNA partnerB) throws CloneNotSupportedException {
        Move[] childMoveSet = new Move[this.moveSet.length];
        Random random = new Random();
        int midPoint = random.nextInt(this.moveSet.length);
        for (int i = 0; i < this.moveSet.length; i++) {
            if (i > midPoint) {
                childMoveSet[i] = this.moveSet[i];
            } else {
                childMoveSet[i] = partnerB.moveSet[i];
            }
        }
        return new DNA(childMoveSet);
    }

    public void becomeXMan(double mutationRate) {
        for (int i = 0; i < moveSet.length; i++) {
            if (new Random().nextDouble() < mutationRate) {
                this.moveSet[i] = getRandomMove();
            }
        }
    }
}
