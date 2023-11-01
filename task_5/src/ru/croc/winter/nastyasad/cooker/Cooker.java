package ru.croc.winter.nastyasad.cooker;

import ru.croc.winter.nastyasad.main.Appliances;

public abstract class Cooker extends Appliances {
    protected int burners;
    public Cooker(String brand, double price, int burners){
    this.brand = brand;
    this.price = price;
    this.category = "Кухонная плита";
    this.burners = burners;
    }

    @Override
    public String characteristics(){
        return "Количество конфорок плиты " + brand + " - " + burners + "шт.";
    }
}
