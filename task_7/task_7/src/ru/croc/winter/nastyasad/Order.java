package ru.croc.winter.nastyasad;


import ru.croc.winter.nastyasad.main.Appliances;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Order {
    protected LocalDate dateCollected;
    protected LocalTime timeCollected;
    private final String clientSurname;
    private final String clientName;
    private final String phoneNumber;
    private final String orderNumber;
    protected OrderStatus status;
    protected LocalDate dateExpired;
    protected final Appliances[] orderList;
    protected final String dateCreation;
    protected final String timeCreation;

    public Order(String clientSurname, String name, String phone, Appliances[] order, String dateCreation, String timeCreation) {
        this.clientSurname = clientSurname;
        this.clientName = name;
        this.phoneNumber = phone;
        this.orderList = order;
        this.dateCreation = dateCreation;
        this.timeCreation = timeCreation;
        this.orderNumber = dateCreation + timeCreation + Integer.toString(Integer.parseInt(phoneNumber) % 10000);
        this.status = OrderStatus.CREATED;
    }

    public String getNumber() {
        return orderNumber;
    }

    protected void orderCollected() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateCollected = LocalDate.now();
        timeCollected = LocalTime.now();
        String dateCollectedString = formatter.format(dateCollected);
        dateExpired = LocalDate.parse(dateCollectedString, formatter).plusWeeks(2);

        status = OrderStatus.COLLECTED;
    }

    protected void orderExpired() throws ExpirationException {
        if (dateExpired != null) {
            if (dateExpired.isBefore(LocalDate.now())) {
                status = OrderStatus.EXPIRED;
            } else throw new ExpirationException(dateExpired);
        }
    }

    protected void orderClosed() {
        status = OrderStatus.CLOSED;
    }


    public String getDateCollected() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateCollectedString = formatter.format(dateCollected);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String newTimeCollected = formatter1.format(timeCollected);

        return dateCollectedString + " " + newTimeCollected;
    }

    public boolean checkAvailability() throws ExpirationException {
        if ((status == OrderStatus.COLLECTED) && dateExpired.isAfter(LocalDate.now())) {
            return true;
        } else if ((status == OrderStatus.COLLECTED) && dateExpired.isBefore(LocalDate.now())) {
            throw new ExpirationException(dateExpired);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDate newDate = LocalDate.parse(dateCreation, formatter);


        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
        LocalTime newTime = LocalTime.parse(timeCreation, formatter1);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm:ss");

        return "Информация о заказе: \n" +
                "Клиент: " + clientSurname + " " + clientName + "\n" +
                "Номер телефона: " + phoneNumber + "\n" +
                "Дата и время поступления: " + formatter2.format(newDate) + " " + formatter3.format(newTime) + "\n" +
                "Номер заказа: " + orderNumber;
    }

    public StringBuilder notification() {
        String str1 = "Уважаемый !\n" + "\n";
        StringBuilder notification = new StringBuilder(str1);
        notification.insert(10, clientName);

        String str2 = "Рады сообщить, что Ваш заказ  готов к выдаче.\n" + "\n";
        StringBuilder strb2 = new StringBuilder(str2);
        strb2.insert(29, orderNumber);
        notification.append(strb2);

        String str3 = "Состав заказа:\n";
        StringBuilder strb3 = new StringBuilder(str3);

        double totalprice = 0.0;

        for (int i = 0; i < orderList.length; i++) {
            if (orderList[i] != null) {
                strb3.append(orderList[i].toString() + "\n");
                totalprice += orderList[i].price;
            } else {
                break;
            }

        }

        notification.append(strb3);

        BigDecimal prize = new BigDecimal(Double.toString(totalprice));
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ROOT);


        String str4 = "\n" + "\n" + "Сумма к оплате: ₽\n";
        StringBuilder strb4 = new StringBuilder(str4);
        strb4.insert(18, format.format(prize));
        notification.append(strb4);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String str5 = "Срок хранения заказа до: .\n" +
                "\n" +
                "С наилучшими пожеланиями, магазин “Кошки и картошки”\n";
        StringBuilder strb5 = new StringBuilder(str5);
        strb5.insert(25, formatter2.format(dateExpired));
        notification.append(strb5);


        return notification;
    }
}
