package ru.croc.winter.nastyasad;

import ru.croc.winter.nastyasad.main.Appliances;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        ForOrder mainInfo = new ForOrder();
        mainInfo.makeAssortment();
        System.out.println("--------------------------------------------- Ассортимент магазина бытовой техники----------------------------------------------------");
        for (int i = 0; i < mainInfo.allProducts.length; i++) {
            System.out.println((i + 1) + ") " + mainInfo.allProducts[i].toString());
        }

        System.out.println();
        System.out.println("Введите свою фамилию: ");
        Scanner scanF = new Scanner(System.in);
        String surname = scanF.next();

        System.out.println("Введите свое имя: ");
        Scanner scanName = new Scanner(System.in);
        String name = scanName.next();
        int length = 0;
        int phoneInt=0;
        while (length!=10){
            String phone="0";
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

        Order ourOrder = new Order(surname, name, phoneInt);

        int flag=1;

        mainInfo.orderList = new Appliances[0];

        while (flag!=0){
            System.out.println("Выбирете товар, который необходимо добавить в заказ(из ассортимента) и введите порядковый номер. Чтобы отправить заказ введите 0: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int number = scan.nextInt();
                if (number == 0) {
                    flag = 0;
                } else if (number > 0 && number < 8) {
                    if (mainInfo.orderList.length + 1 <= 75) {
                        Appliances[] newOrderList = new Appliances[mainInfo.orderList.length + 1];
                        for (int i = 0; i < mainInfo.orderList.length; i++) {
                            newOrderList[i] = mainInfo.orderList[i];
                        }
                        newOrderList[newOrderList.length - 1] = mainInfo.allProducts[number - 1];
                        mainInfo.orderList = newOrderList;

                        System.out.println("-----------------------------------Ваш текущий заказ------------------------------------------");
                        for (int i = 0; i < mainInfo.orderList.length; i++) {
                            System.out.println((i + 1) + ") " + mainInfo.orderList[i].toString());
                        }
                    }
                }
                else {
                    System.out.println("Неверное число, необходимо выбрать товары от 1 до 8");
                }
            }
            else {
                System.out.println("Необходимо ввести число от 0 до 8!");
            }
        }
        System.out.println();
        System.out.println("-------------------------------------------Создали заказ----------------------------------------------------");
        for (int i = 0; i < mainInfo.orderList.length; i++) {
            System.out.println((i + 1) + ") " + mainInfo.orderList[i].toString());
        }
        System.out.println("Ваш номер заказа: "+ourOrder.getNumber());
        ourOrder.status=OrderStatus.CREATED;
        System.out.println("Статус заказа: " + ourOrder.status);

        try {
            System.out.println("Попытка забрать заказ: " + ourOrder.checkOrderStatus()); /*при истечении срока на получение заказа выбрасывает ошибку*/
        } catch (Exception e) {
            System.out.println("Истек срок получения заказа");
        }


        /* Здесь планировала вставить декоратор с задержкой времени, но не успела его реализовать, поэтому пока просто подряд изменяется статус заказа*/


        ourOrder.status=OrderStatus.COLLECTED;
        System.out.println("Статус заказа: "+ourOrder.status);
        System.out.println("Завершения сборки в : "+ourOrder.buildDate()+"\n");

        try {
            System.out.println("Попытка забрать заказ: " + ourOrder.checkOrderStatus()); /*при истечении срока на получение заказа выбрасывает ошибку*/
        } catch (Exception e) {
            System.out.println("Истек срок получения заказа");
        }

        System.out.println("Уважаемый " + ourOrder.clientName+"!\n" +
                "Рады сообщить, что Ваш заказ номер " + ourOrder.orderNumber + " готов к выдаче.\n" +
                "Состав заказа:\n");
        double orderPrice=0;
        for (int i = 0; i < mainInfo.orderList.length; i++) {
            System.out.println((i + 1) + ") " + mainInfo.orderList[i].toString());
        }

        System.out.println("Сумма к оплате: "+ orderPrice +"₽\n" +
                "\n" +
                "Срок хранения заказа:"+ ourOrder.dateExpiredString +".\n" +
                "\n" +
                "С наилучшими пожеланиями, магазин “Кошки и картошки”\n");


    }
}