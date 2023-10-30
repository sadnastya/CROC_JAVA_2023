package ru.croc.winter;

public class Rectangle extends Figure{
    protected int x1_leftLower;
    protected int y1_leftLower;
    protected int x2_rightUpper;
    protected int y2_rightUpper;

    protected final String figureName = "прямоугольник";
    public Rectangle(int x1, int y1, int x2, int y2){
        this.x1_leftLower = x1;
        this.y1_leftLower = y1;
        this.x2_rightUpper = x2;
        this.y2_rightUpper = y2;
    }

    @Override
    public void move(int dx, int dy) {
        x1_leftLower=x1_leftLower+dx;
        y1_leftLower=y1_leftLower+dy;
        x2_rightUpper=x2_rightUpper+dx;
        y2_rightUpper=y2_rightUpper+dy;

    }
}
