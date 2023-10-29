package ru.croc.winter.nastyasad;

import ru.croc.winter.nastyasad.cooker.Cooker;
import ru.croc.winter.nastyasad.cooker.ElectricCooker;
import ru.croc.winter.nastyasad.cooker.GasCooker;
import ru.croc.winter.nastyasad.cooker.InductionCooker;
import ru.croc.winter.nastyasad.fridge.Fridge;
import ru.croc.winter.nastyasad.fridge.FridgeFreez;
import ru.croc.winter.nastyasad.fridge.MiniFridge;
import ru.croc.winter.nastyasad.wacher.Washer;
import ru.croc.winter.nastyasad.wacher.WasherDryer;


public class

Program{
    protected static Appliances[] allProducts;

    public static void main(String[] args) {
        allProducts = new Appliances[8];

        System.out.println("--------------------------------------------- Ассортимент магазина бытовой техники----------------------------------------------------");

        Appliances fridge1 = new ImportDecorator(new FridgeFreez("dexp", 25000, "3.5-7"), "Эстония", 5);
        allProducts[0]=fridge1;

        Appliances fridge2 = new MiniFridge("Василиса", 50000, "3-5");
        allProducts[1]=fridge2;

        Appliances fridge3 = new PowerDecorator(new ImportDecorator(new Fridge("samsung", 2000, "2-3"), "Марокко", 5), 2.5);
        allProducts[2]=fridge3;

        Appliances cooker1 = new PowerDecorator( new InductionCooker("Витязь", 12500, 4), 7.3);
        allProducts[3]=cooker1;

        Appliances cooker2 = new ImportDecorator(new PowerDecorator(new ElectricCooker("garange", 25000, 5), 8), "Чехия", 5);
        allProducts[4] = cooker2;

        Appliances cooker3 = new PowerDecorator(new ImportDecorator(new GasCooker("Gefest", 30000, 4), "Беларусь", 3), 4.6);
        allProducts[5] = cooker3;

        Appliances washer1 = new ImportDecorator(new PowerDecorator(new Washer("haier", 35000, 10), 8), "Китай", 7);
        allProducts[6] = washer1;

        Appliances washer2 = new PowerDecorator(new WasherDryer("Hi", 50000, 12), 6.7);
        allProducts[7] = washer2;


        for (int i = 0; i < allProducts.length; i++) {
            System.out.println((i + 1) + ") " + allProducts[i].toString());
        }


        System.out.println();

        System.out.println("Установили новое значение для 1 строчки, изменили цену и бренд на мини-холодильник: ");
        fridge2.setPrice(33000);
        fridge2.setBrand("lg");
        System.out.println(fridge2.toString());

        System.out.println();

        System.out.println("---------------------------------- Дополнительные характеристики бытовой техники--------------------------------------------");

        for (int i = 0; i < allProducts.length; i++) {
            System.out.println((i + 1) + ") " + allProducts[i].characteristics());
        }
    }
}