package com.pkawa.mazegenalg;

public class Labirynth {
    public static final Position STARTING_POSITION = new Position(1, 1);
    public static final Position ENDING_POSITION = new Position(11, 11);
    private static final String CAN_PASS_SIGN = "▮";
    private static final String CANNOT_PASS_SIGN = "▯";

    private static boolean[][] movableMap = new boolean[][]{
        {false, false, false, false, false, false, false, false, false, false, false, false},
        {false, true, true, true, false, true, true, true, false, true, true, false},
        {false, false, false, true, true, true, false, true, false, false, true, false},
        {false, true, true, true, false, true, false, true, true, true, true, false},
        {false, true, false, true, false, false, true, true, false, false, true, false},
        {false, true, true, false, false, true, true, true, false, true, true, false},
        {false, true, true, true, true, true, false, true, true, true, false, false},
        {false, true, false, true, true, false, false, true, false, true, true, false},
        {false, true, false, false, false, true, true, true, false, false, true, false},
        {false, true, false, true, false, false, true, false, true, false, true, false},
        {false, true, false, true, true, true, true, true, true, true, true, false},
        {false, false, false, false, false, false, false, false, false, false, false, false},
    };

    public static boolean[][] getMovableMap() {
        return movableMap;
    }

    public static boolean CanGoToThatPosition(Position position) {
        return movableMap[position.getColumn()][position.getRow()];
    }

    public static void printMap() {
        for (int i = 0; i < movableMap.length; i++) {
            for (int j = 0; j < movableMap.length; j++) {
                if (movableMap[i][j]) {
                    System.out.print(CANNOT_PASS_SIGN);
                } else {
                    System.out.print(CAN_PASS_SIGN);
                }
            }
            System.out.println();
        }
    }
}
