package ru.croc.winter.nastyasad;

public abstract class ProductDecorator extends Appliances{
    protected Appliances appliances;
    public ProductDecorator(Appliances appliances){
        this.appliances=appliances;
    }

    public String toString() {
        return appliances.toString();
    }

    public String characteristics(){
        return appliances.characteristics();
    }


}
