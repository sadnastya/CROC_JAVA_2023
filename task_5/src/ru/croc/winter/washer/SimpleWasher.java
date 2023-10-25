package ru.croc.winter.washer;

public class SimpleWasher extends Washer {
    protected String sub_category=" обычная, без сушилки";
    public SimpleWasher(String brand, String country, double price){
        super(brand, price);
        this.country=country;
        this.guarantee= 5;
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + sub_category;
    }
}