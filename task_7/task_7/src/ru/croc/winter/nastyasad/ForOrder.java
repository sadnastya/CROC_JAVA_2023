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
import java.util.Scanner;

public class ForOrder {

    public Appliances[] orderList;

    public static Appliances[] allProducts = {
            new ImportDecorator(new FridgeFreez("dexp", 25000, "3.5-7"), "Эстония", 5),
            new MiniFridge("Василиса", 50000, "3-5"),
            new PowerDecorator(new ImportDecorator(new Fridge("samsung", 2000, "2-3"), "Марокко", 5), 2.5),
            new PowerDecorator(new InductionCooker("Витязь", 12500, 4), 7.3),
            new ImportDecorator(new PowerDecorator(new ElectricCooker("garange", 25000, 5), 8), "Чехия", 5),
            new PowerDecorator(new ImportDecorator(new GasCooker("Gefest", 30000, 4), "Беларусь", 3), 4.6),
            new ImportDecorator(new PowerDecorator(new Washer("haier", 35000, 10), 8), "Китай", 7),
            new PowerDecorator(new WasherDryer("Hi", 50000, 12), 6.7),
    };

    public void makeOrderList() throws OrderSizeException {
        System.out.println();
        int flag = 1;
        orderList = new Appliances[75];
        int k = 0;
        while (flag != 0) {
            System.out.println("Выбирете товар, который необходимо добавить в заказ(из ассортимента) и введите порядковый номер. Чтобы отправить заказ введите 0: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int number = scan.nextInt();
                if (number == 0) {
                    flag = 0;
                } else if (number > 0 && number < 9) {
                    k += 1;
                    try {
                        orderList[k - 1] = allProducts[number - 1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new OrderSizeException(5);
                    }
                    System.out.println("-----------------------------------Ваш текущий заказ------------------------------------------");
                    for (int i = 0; i < k; i++) {
                        System.out.println((i + 1) + ") " + orderList[i].toString());
                    }

                } else {
                    System.out.println("Неверное число, необходимо выбрать товары от 1 до 8");
                }
            } else {
                System.out.println("Необходимо ввести число от 0 до 8!");
            }
        }
    }

    public static void printAssortment() {
        for (int i = 0; i < allProducts.length; i++) {
            System.out.println(i + 1 + ") " + allProducts[i].toString());
        }
    }
}
