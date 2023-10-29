package ru.croc.winter.nastyasad.fridge;

import ru.croc.winter.nastyasad.Appliances;

public class Fridge extends Appliances {
    protected String temperature;
    public Fridge(String brand, double price, String temperature){
        this.brand = brand;
        this.price = price;
        this.category = "Холодильник";
        this.temperature = temperature;
    }

    @Override
    public String characteristics(){
        return "Средняя температура холодильника " + brand + " = " + temperature + " градуса";
    }
}
