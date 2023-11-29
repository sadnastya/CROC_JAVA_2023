package ru.croc.winter.nastyasad;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        AuctionLot firstLot = LotsAndUsersReader.readLot("lot.txt", LocalDateTime.now().plusSeconds(1));

        ArrayList<User> users = LotsAndUsersReader.readUsers("participants.txt", firstLot);

        for (int i = 0; i < users.size(); i++) {
            new Thread(users.get(i)).start();
        }

        firstLot.waitCloseLot(); //основной поток проверяет закончились ли торги(по времени или были ли они завершены в ручную)
        System.out.println();
        try {
            System.out.println(firstLot.getWinnerName());
        } catch (LotNotSoldException e) {
            System.out.println("Лот еще не продан, победитель не определен");
        }

        System.out.println(firstLot.getCurrentPrice());
    }
}