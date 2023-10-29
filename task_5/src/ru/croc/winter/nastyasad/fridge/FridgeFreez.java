package ru.croc.winter.nastyasad.fridge;

public class FridgeFreez extends Fridge {
    protected String sub_category="с морозильной камерой";
    public FridgeFreez(String brand, double price, String temperature){
        super(brand, price, temperature);
    }

    @Override
    public String toString() {
        return category + " наименование: " + brand + ", цена: " + price + ",  категория: " + sub_category;
    }
}
