package ru.croc.winter.cooker;

import ru.croc.winter.cooker.Cooker;

public class CookerElectric extends Cooker {
    protected String sub_category="элеткрическая";
    public CookerElectric(String brand, String country, double price){
        super(brand, price);
        this.country=country;
        this.guarantee= 4;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}
