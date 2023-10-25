package ru.croc.winter.washer;

import ru.croc.winter.MainChar;

public abstract class Washer extends MainChar {
    public Washer(String brand, double price){
        this.category = "Стиральная машинка";
        this.brand = brand;
        this.price = price;
    }
}
