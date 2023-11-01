package ru.croc.winter.nastyasad.main;

public class PowerDecorator extends ProductDecorator {
    protected double power;

    public PowerDecorator(Appliances appliances, double power){
        super(appliances);
        this.power=power;
    }

    @Override
    public String characteristics(){
        return super.characteristics() + ", мощность аккамулятора: "+power;
    }
}
