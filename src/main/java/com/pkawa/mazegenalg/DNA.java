package com.pkawa.mazegenalg;

import java.util.Random;

public class DNA {

    private Move[] moveSet = new Move[40];

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
        Position finalPosition = walkToTheTarget();
        calculateFitness(finalPosition);
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
                    if (currentDNAPosition.canMoveRight()) currentDNAPosition.moveRight();
                    break;
                case DOWN:
                    if (currentDNAPosition.canMoveDown()) currentDNAPosition.moveDown();
                    break;
                case UP:
                    if (currentDNAPosition.canMoveUp()) currentDNAPosition.moveUp();
                    break;
                case LEFT:
                    if (currentDNAPosition.canMoveLeft()) currentDNAPosition.moveLeft();
                    break;
                default:
                    throw new IllegalArgumentException("Can't move in any direction");
            }
        }
        return currentDNAPosition;
    }

    private void calculateFitness(Position finalPosition) throws CloneNotSupportedException {
        int resultAchievedScore =
                (finalPosition.getColumn() == Labirynth.ENDING_POSITION.getColumn()
                &&
                        finalPosition.getRow() == Labirynth.ENDING_POSITION.getRow())
                        ? 200 :
                        (finalPosition.getColumn() + finalPosition.getRow());
        int leftStepsScore = finalPosition.getLeftSteps();
        int howCloseToEndScore =
                (finalPosition.getColumn() +
                finalPosition.getRow() -
                        Labirynth.ENDING_POSITION.getRow() -
                        Labirynth.ENDING_POSITION.getColumn()) * 2;

        this.fitnessScore = resultAchievedScore + leftStepsScore + howCloseToEndScore;
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
        DNA childDNA = new DNA(childMoveSet);
        return childDNA;
    }

    public void becomeXMan(double mutationRate) {
        for (int i = 0; i < moveSet.length; i++) {
            if (new Random().nextDouble() < mutationRate) {
                this.moveSet[i] = getRandomMove();
            }
        }
    }
}
