package io.github.lhug.adventofcode.common;

public enum Direction {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    private final int diffX;
    private final int diffY;

    Direction(int diffX, int diffY) {
        this.diffX = diffX;
        this.diffY = diffY;
    }

    public int diffX() { return diffX; }
    public int diffY() { return diffY; }
}
