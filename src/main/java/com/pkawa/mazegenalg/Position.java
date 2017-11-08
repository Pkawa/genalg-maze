package com.pkawa.mazegenalg;

public class Position {
    private int column = 1;
    private int row = 1;

    private int leftSteps = 40;

    boolean canMoveUp() throws CloneNotSupportedException {
        Position pos = this.clone();
        pos.moveUp();
        return Labirynth.CanGoToThatPosition(pos);
    }

    boolean canMoveDown() throws CloneNotSupportedException {
        Position pos = new Position(this.column, this.row);
        pos.moveDown();
        return Labirynth.CanGoToThatPosition(pos);
    }

    boolean canMoveLeft() throws CloneNotSupportedException {
        Position pos = this.clone();
        pos.moveLeft();
        return Labirynth.CanGoToThatPosition(pos);
    }

    boolean canMoveRight() throws CloneNotSupportedException {
        Position pos = this.clone();
        pos.moveRight();
        return Labirynth.CanGoToThatPosition(pos);
    }

    public Position() {
    }

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public void moveUp() {
        this.row -= 1;
        leftSteps -= 1;
    }

    public void moveDown() {
        this.row += 1;
        leftSteps -= 1;
    }

    public void moveLeft() {
        this.column -= 1;
        leftSteps -= 1;
    }

    public void moveRight() {
        this.column += 1;
        leftSteps -= 1;
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                ", leftSteps=" + leftSteps +
                '}';
    }

    @Override
    public Position clone() throws CloneNotSupportedException {
        return new Position(this.column, this.row);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getLeftSteps() {
        return leftSteps;
    }

}
