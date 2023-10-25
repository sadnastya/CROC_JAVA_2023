package ru.croc.winter.washer;

import ru.croc.winter.washer.Washer;

public class WasherDryer extends Washer {
    protected String sub_category="с сушилкой";
    public WasherDryer(String brand, String country, double price){
        super(brand, price);
        this.country=country;
        this.guarantee=7;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}
