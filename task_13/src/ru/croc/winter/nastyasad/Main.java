package ru.croc.winter.nastyasad;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        AuctionLot firstLot = LotsAndUsersReader.readLot("lot.txt");

        ArrayList<User> users = LotsAndUsersReader.readUsers("participants.txt", firstLot);

        for (int i = 0; i < users.size(); i++) {
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