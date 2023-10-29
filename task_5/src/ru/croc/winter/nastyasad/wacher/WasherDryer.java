package ru.croc.winter.nastyasad.wacher;

public class WasherDryer extends Washer{
    protected String subCategory="С сушилкой";
    public WasherDryer(String brand, double price, int weight){
        super(brand, price, weight);
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + subCategory;
    }
}
