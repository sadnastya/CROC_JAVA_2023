package ru.croc.winter.fridge;

import ru.croc.winter.fridge.Fridge;

public class FridgeSimple extends Fridge {
    protected String sub_category="обычный, без морозильной камеры";
    public FridgeSimple(String brand, String country, double price){
        super(brand, price);
        this.country=country;
        this.guarantee= 6;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}
