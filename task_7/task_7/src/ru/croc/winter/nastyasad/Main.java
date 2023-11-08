package ru.croc.winter.nastyasad;

import ru.croc.winter.nastyasad.main.Appliances;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Order> allOrders = new ArrayList<Order>();

    public static void main(String[] args) {

        System.out.println("Введите свою фамилию: ");
        Scanner scanF = new Scanner(System.in);
        String surname = scanF.next();

        System.out.println("Введите свое имя: ");
        Scanner scanName = new Scanner(System.in);
        String name = scanName.next();
        int length = 0;
        int phoneInt = 0;
        String phone = "0";
        while (length != 10) {
            System.out.println("Введите номер телефона(10 цифр, без 8): +7...");
            Scanner scanPhone = new Scanner(System.in);
            phone = scanPhone.next();
            try {
                phoneInt = Integer.parseInt(phone);
                length = phone.length();
            } catch (NumberFormatException e) {
                System.err.println("Должны быть только целые числа!");
            }
        }

        ForOrder MainInfo = new ForOrder();


        System.out.println("--------------------------------------------- Ассортимент магазина бытовой техники----------------------------------------------------");
        MainInfo.printAssortment();
        try {
            MainInfo.makeOrderList();
        } catch (OrderSizeException e) {
            System.out.println(e.getMessage());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");

        allOrders.add(new Order(surname, name, phone, MainInfo.orderList, formatter.format(LocalDate.now()), formatter1.format(LocalTime.now())));

        System.out.println("-------------------------------------------Текущие заказы и их статусы----------------------------------------------------");
        System.out.println(allOrders.toString());
        Order ourOrder = allOrders.get(0);


        System.out.println(ourOrder.notification());


    }
}