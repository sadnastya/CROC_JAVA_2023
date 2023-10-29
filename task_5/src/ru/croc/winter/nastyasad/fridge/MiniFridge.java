package ru.croc.winter.nastyasad.fridge;

public class MiniFridge extends Fridge {
    protected String subCategory = "Мини-холодильник";

    public MiniFridge(String brand, double price, String temperature) {

        super(brand, price, temperature);
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + subCategory;
    }
}
