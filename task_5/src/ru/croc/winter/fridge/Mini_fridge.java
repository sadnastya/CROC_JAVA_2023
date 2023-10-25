package ru.croc.winter.fridge;

import ru.croc.winter.fridge.Fridge;

public class Mini_fridge extends Fridge {
    protected String sub_category = "Мини-холодильник";

    public Mini_fridge(String brand, String country, double price) {

        super(brand, price);
        this.country = country;
        this.guarantee= 2;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}
