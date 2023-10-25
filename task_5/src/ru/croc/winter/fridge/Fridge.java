package ru.croc.winter.fridge;

import ru.croc.winter.MainChar;

public abstract class Fridge extends MainChar {
    public Fridge(String brand, double price){
        this.category = "Холодильник";
        this.brand = brand;
        this.price = price;
    }
}
