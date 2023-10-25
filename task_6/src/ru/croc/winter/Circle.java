package ru.croc.winter;

public class Circle extends Annotation{
    protected float x0_center;
    protected float y0_center;
    protected float radius;

    Circle(float x0_center, float y0_center, float radius){
        super("Круг", "Это круг");
        this.x0_center = x0_center;
        this.y0_center = y0_center;
        this.radius = radius;

    }

    @Override
    public String toString(){
        return figure + " центр: (" + x0_center +", " + y0_center + "), радиус: " + radius +" " + signature;
    }
}
