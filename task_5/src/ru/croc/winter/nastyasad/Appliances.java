package ru.croc.winter.nastyasad;

public abstract class Appliances {
    public String category;
    protected double price;
    protected String brand;
    protected String country;
    protected int guarantee;

    @Override
    public String toString() {
        return category + " наименование: " + brand + ", цена: " + this.price;
    }
    public abstract String characteristics();


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand){
        this.brand=brand;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }

}