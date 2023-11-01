package ru.croc.winter.nastyasad;
import ru.croc.winter.nastyasad.cooker.ElectricCooker;
import ru.croc.winter.nastyasad.cooker.GasCooker;
import ru.croc.winter.nastyasad.cooker.InductionCooker;
import ru.croc.winter.nastyasad.fridge.Fridge;
import ru.croc.winter.nastyasad.fridge.FridgeFreez;
import ru.croc.winter.nastyasad.fridge.MiniFridge;
import ru.croc.winter.nastyasad.main.Appliances;
import ru.croc.winter.nastyasad.main.ImportDecorator;
import ru.croc.winter.nastyasad.main.PowerDecorator;
import ru.croc.winter.nastyasad.wacher.Washer;
import ru.croc.winter.nastyasad.wacher.WasherDryer;

import java.time.LocalDate;

public class ForOrder {
    public static Appliances[] allProducts;

    public Appliances[] orderList;

    public void makeAssortment(){
        allProducts = new Appliances[8];

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
    }

}
