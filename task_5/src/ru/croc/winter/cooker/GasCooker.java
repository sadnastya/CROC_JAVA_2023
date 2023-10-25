package ru.croc.winter.cooker;

import ru.croc.winter.cooker.Cooker;

public class GasCooker extends Cooker {
    protected String sub_category="газовая";
    public GasCooker(String brand, String country, double price){
        super(brand, price);
        this.country=country;
        this.guarantee= 9;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}
