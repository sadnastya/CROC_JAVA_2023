package ru.croc.winter;

import ru.croc.winter.cooker.CookerElectric;
import ru.croc.winter.cooker.CookerInduction;
import ru.croc.winter.cooker.GasCooker;
import ru.croc.winter.fridge.FridgeFreez;
import ru.croc.winter.fridge.FridgeSimple;
import ru.croc.winter.fridge.Mini_fridge;
import ru.croc.winter.washer.SimpleWasher;
import ru.croc.winter.washer.WasherDryer;


public class Program{
    protected static Object[] all_products;

    public static void main(String[] args) {

        System.out.println("-------------------- Ассортимент магазина бытовой техники--------------------------");

        all_products = new Object[8];

        Mini_fridge fridge_n1 = new Mini_fridge("dexp", "Эстония", 10000);
        all_products[0] = fridge_n1;

        FridgeFreez fridge_n2 = new FridgeFreez("haier", "Китай", 18000);
        all_products[1] = fridge_n2;

        FridgeSimple fridge_n3 = new FridgeSimple("huawei", "Россия", 15500);
        all_products[2] = fridge_n3;

        CookerInduction cooker_1 = new CookerInduction("samsung", "Норвегия",35000);
        all_products[3] = cooker_1;

        CookerElectric cooker_2 = new CookerElectric("haier", "Германия",27000);
        all_products[4] = cooker_2;

        GasCooker cooker_3 = new GasCooker("LG", "Австрия",18000);
        all_products[5] = cooker_3;

        WasherDryer washer_1 = new WasherDryer("dexp", "Россия",32000);
        all_products[6] = washer_1;

        SimpleWasher washer_2 = new SimpleWasher("samsung", "Италия",39000);
        all_products[7] = washer_2;



        for (int i = 0; i < all_products.length; i++) {
            System.out.println((i + 1) + ") " + all_products[i].toString());
        }

        System.out.println("");

        System.out.println("Установили новое значение для 1 строчки, изменили цену на мини-холожильник: ");
        fridge_n1.setPrice(13000);
        System.out.println(fridge_n1.toString());

        System.out.println("");


        System.out.println("-----------------Информация по стране-производителю и гарантии для импортных товаров-------------------");

        fridge_n1.getImportDeviceInform();
        fridge_n2.getImportDeviceInform();
        fridge_n3.getImportDeviceInform();
        cooker_1.getImportDeviceInform();
        cooker_2.getImportDeviceInform();
        cooker_3.getImportDeviceInform();
        washer_1.getImportDeviceInform();
        washer_2.getImportDeviceInform();
    }
}