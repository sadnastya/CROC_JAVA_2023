package ru.croc.winter.nastyasad.ClientApp;

import ru.croc.winter.nastyasad.PollingStation;
import ru.croc.winter.nastyasad.Vote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        PollingStation station = new PollingStation(1);
        station.readVotesFromCSV("votes.csv");

        try (Socket socket = new Socket("localhost", 8010);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner serverScanner = new Scanner(socket.getInputStream())) {
            station.serializeAndSend(socket);
            System.out.println("1"+socket.isClosed());


           Thread serverListener = new Thread(() -> {
                try {
                    // Ожидаем и выводим ответ от сервера
                    System.out.println("2"+socket.isClosed());
                    String serverResponse = in.readLine();

                    System.out.println("Answer from server: " + serverResponse);
                } catch (SocketException e) {
                    System.out.println("Сокет закрыт");
                } catch (IOException e) {
                    e.getMessage();
                }});



            serverListener.start();



            serverListener.join();


        } catch (IOException e) {
            e.getMessage();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}