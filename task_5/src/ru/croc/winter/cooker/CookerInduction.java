package ru.croc.winter.cooker;

import ru.croc.winter.cooker.Cooker;

public class CookerInduction extends Cooker {
    protected String sub_category=" индукционная ";
    public CookerInduction(String brand, String country, double price){
        super(brand, price);
        this.country=country;
        this.guarantee= 6;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}
