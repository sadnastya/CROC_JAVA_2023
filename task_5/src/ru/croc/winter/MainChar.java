package ru.croc.winter;

public abstract class MainChar {
    protected double price;
    protected String brand;
    protected String country;
    protected int guarantee;
    public String category;

    public void getImportDeviceInform(){
        if(this.country!="Россия"){
            System.out.println(this.category +" "+ this.brand + " страна-производитель: "+this.country + ", срок гарантии(в годах): " + this.guarantee);
        }
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand){
        this.brand=brand;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price=price;
    }

}