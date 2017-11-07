package com.pkawa.mazegenalg;

public class Position {
    private int column;
    private int row;

    private int leftSteps = 40;

    boolean canMoveUp() throws CloneNotSupportedException {
        Position pos = this.clone();
        pos.moveUp();
        return Labirynth.CanGoToThatPosition(pos);
    }

    boolean canMoveDown() throws CloneNotSupportedException {
        Position pos = this.clone();
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
        column = 1;
        row = 1;
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
    public Position clone() {
        return new Position(this.column, this.row);
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getLeftSteps() {
        return leftSteps;
    }

    public void setLeftSteps(int leftSteps) {
        this.leftSteps = leftSteps;
    }

}
