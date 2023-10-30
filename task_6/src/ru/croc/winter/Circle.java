package ru.croc.winter;

public class Circle extends Figure{
    protected int x0_center;
    protected int y0_center;
    protected int radius;

    protected final String figureName="круг";

    public Circle(int x0_center, int y0_center, int radius) {
        this.x0_center = x0_center;
        this.y0_center = y0_center;
        this.radius = radius;

    }

    @Override
    public void move(int dx, int dy) {
        x0_center=x0_center+dx;
        y0_center=y0_center+dy;
    }
}
