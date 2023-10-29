package ru.croc.winter.nastyasad.wacher;

import ru.croc.winter.nastyasad.Appliances;

public class Washer extends Appliances {
    protected int weight;
    public Washer(String brand, double price, int weight){
        this.brand = brand;
        this.price = price;
        this.category = "Стиральная машина";
        this.weight = weight;
    }

    @Override
    public String characteristics(){
        return "Максимальная загрузкая белья стиральной машины " + brand + " = " + weight + "кг.";
    }
}
