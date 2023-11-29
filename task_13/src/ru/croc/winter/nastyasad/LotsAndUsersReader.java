package ru.croc.winter.nastyasad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LotsAndUsersReader {
    public static AuctionLot readLot(String fileName) {
        String lotName;
        BigDecimal lotPrice;
        try (BufferedReader r = new BufferedReader(new FileReader(fileName))) {
            lotName = r.readLine();
            lotPrice = new BigDecimal(r.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new AuctionLot(lotName, lotPrice, LocalDateTime.now().plusDays(2));
    }

    public static ArrayList<User> readUsers(String fileName, AuctionLot lot) {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader("participants.txt"))) {
            String line;
            while ((line = r.readLine()) != null) {
                users.add(new User(line, lot));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
