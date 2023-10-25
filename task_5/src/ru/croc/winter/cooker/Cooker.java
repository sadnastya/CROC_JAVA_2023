package ru.croc.winter.cooker;

import ru.croc.winter.MainChar;

public abstract class Cooker extends MainChar {
    Cooker(String brand, double price){
        this.category = "Плита";
        this.brand = brand;
        this.price = price;
    }
}
