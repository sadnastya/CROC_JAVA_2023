package ru.croc.winter;

public class Rectangle extends Annotation{
    protected float x1_leftLower;
    protected float y1_leftLower;
    protected float x2_rightUpper;
    protected float y2_rightUpper;
    Rectangle(float x1, float y1, float x2, float y2){
        super("Прямоугольник", "Это прямоугольник");
        this.x1_leftLower = x1;
        this.y1_leftLower = y1;
        this.x2_rightUpper = x2;
        this.y2_rightUpper = y2;
    }

    @Override
    public String toString(){
        return figure + " левый нижний угол: (" + x1_leftLower +", " + y1_leftLower + "), правый верхний угол: (" + x2_rightUpper +", " + y2_rightUpper + ") " + signature;
    }
}
