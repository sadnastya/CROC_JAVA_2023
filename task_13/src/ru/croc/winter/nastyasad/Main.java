package ru.croc.winter.nastyasad;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String lotName;
        BigDecimal lotPrice;
        try (BufferedReader r = new BufferedReader(new FileReader("lot.txt"))) {
            lotName= r.readLine();
            lotPrice = new BigDecimal(r.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<User> users = new ArrayList<>();

        AuctionLot firstLot = new AuctionLot(lotName, lotPrice, LocalDateTime.now().plusDays(2));

        try (BufferedReader r = new BufferedReader(new FileReader("participants.txt"))) {
            String line;
            while ((line = r.readLine()) != null) {
                users.add(new User(line, firstLot));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i=0; i< users.size(); i++){
            new Thread(users.get(i)).start();
        }

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        firstLot.closeLot();
        System.out.println();


        System.out.println(firstLot.getWinnerName());
        System.out.println(firstLot.getCurrentPrice());
    }


}