package ru.croc.winter.nastyasad.cooker;

public class GasCooker extends Cooker{
    protected String subCategory="Газовая";
    public GasCooker(String brand, double price, int burners){
        super(brand, price, burners);
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + subCategory;
    }
}
