package ru.croc.winter.fridge;

import ru.croc.winter.fridge.Fridge;

public class FridgeFreez extends Fridge {
    protected String sub_category="с морозильной камерой";
    public FridgeFreez(String brand, String country, double price){
        super(brand, price);
        this.country = country;
        this.guarantee= 10;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}
