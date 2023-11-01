package ru.croc.winter.nastyasad;

import ru.croc.winter.nastyasad.main.Appliances;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Order extends ForOrder{
    protected LocalDate dateCollected;
    protected String clientSurname;
    protected String clientName;
    protected int phoneNumber;
    protected String dateExpiredString;
    protected String orderNumber;
    protected OrderStatus status;

    protected LocalDate dateExpired;

    public Order(String clientSurname, String name, int phone){
        this.clientSurname = clientSurname;
        this.clientName = name;
        this.phoneNumber = phone;
    }

    public String getNumber(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String dateCreation = formatter.format( LocalDate.now() );

        orderNumber = dateCreation;

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
        String timeCreation = formatter1.format( LocalTime.now() );
        orderNumber = orderNumber + timeCreation+Integer.toString(phoneNumber%10000);
        return orderNumber;
    }

    public String buildDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateCollectedString = formatter.format( LocalDate.now() );
        dateExpired = LocalDate.parse(dateCollectedString, formatter).plusWeeks(2);
        dateExpiredString = formatter.format( dateExpired );


        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeCollected = formatter1.format( LocalTime.now() );
        return dateCollectedString +" "+ timeCollected;
    }

    public boolean checkOrderStatus() throws Exception{
        if ((status==OrderStatus.COLLECTED) && dateExpired.isAfter(LocalDate.now())){
            return true;
        }
        else if ((status==OrderStatus.COLLECTED) && dateExpired.isBefore(LocalDate.now())){
            throw  new Exception("Заказ собран, но срок хранения истёк");
        }
        else {
            return false;
        }
    }

}
