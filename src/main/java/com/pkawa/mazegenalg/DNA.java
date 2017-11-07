package com.pkawa.mazegenalg;

import java.util.Random;

public class DNA {

    private Move[] moveSet = new Move[40];

    private int fitnessScore = Integer.MIN_VALUE;

    public DNA() {
        Random random = new Random();
        for(int i = 0; i < moveSet.length; i++) {
            int randomNumber = random.nextInt(4);

            Move result;
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

            moveSet[i] = result;
        }
    }

    public void walkToTheTarget() throws CloneNotSupportedException {
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
            }
        }
        calculateFitness(currentDNAPosition);
    }

    private void calculateFitness(Position finalPosition) throws CloneNotSupportedException {
        int resultAchievedScore =
                (finalPosition.getColumn() == Labirynth.ENDING_POSITION.getColumn()
                && finalPosition.getRow() == Labirynth.ENDING_POSITION.getRow()) ? 200 : 0;
        int leftStepsScore = finalPosition.getLeftSteps() * 5;
        int howCloseToEndScore =
                (finalPosition.getColumn() +
                finalPosition.getRow() -
                        Labirynth.ENDING_POSITION.getRow() -
                        Labirynth.ENDING_POSITION.getColumn()) * 10;

        this.fitnessScore = resultAchievedScore + leftStepsScore + howCloseToEndScore;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public Move[] getMoveSet() {
        return moveSet;
    }

}
