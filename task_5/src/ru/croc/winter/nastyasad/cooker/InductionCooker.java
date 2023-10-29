package ru.croc.winter.nastyasad.cooker;

public class InductionCooker extends Cooker{
    protected String subCategory="Индукционная";
    public InductionCooker(String brand, double price, int burners){
        super(brand, price, burners);
    }

    @Override
    public String toString() {
        return this.category + " наименование: " + this.brand + ", цена: " + this.price + ",  категория: " + subCategory;
    }
}
