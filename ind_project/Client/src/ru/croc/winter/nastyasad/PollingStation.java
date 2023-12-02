package ru.croc.winter.nastyasad;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PollingStation {
    private final int idStation;
    private List<Vote> votes = new ArrayList<>();

    public PollingStation(int idStation) {
        this.idStation = idStation;
    }

    public List<Vote> readVotesFromCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS");
                    LocalDateTime timestamp = LocalDateTime.parse(parts[0], formatter);
                    int candidateId = Integer.parseInt(parts[1].trim());
                    votes.add(new Vote(timestamp, candidateId));
                } else {
                    throw new RuntimeException("Неверный формат данных");
                }
            }
            return votes;
        }

    }

    public void serializeAndSend(Socket socket) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
            System.out.println("Голоса отправлены на сервер");
            outputStream.writeObject(votes);
            System.out.println(votes);
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
                out.println(idStation);
                System.out.println("4"+socket.isClosed());
            }
            System.out.println("3"+socket.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Пункт приема голосов: "+idStation;
    }
}
