package ru.croc.winter.nastyasad;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.List;


class Server {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "");
            DBCreator ourDB = new DBCreator(conn);
            //база кандидатов задается на сервере
            String insertCand = "INSERT INTO Candidate(name, surName) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(insertCand)) {
                statement.setString(1, "Медведь");
                statement.setString(2, "Собачкин");
                statement.executeUpdate();
                statement.setString(1, "Енот");
                statement.setString(2, "Кошечкин");
                statement.executeUpdate();
                statement.setString(1, "Лебедь");
                statement.setString(2, "Воробьев");
                statement.executeUpdate();
                statement.setString(1, "Лиса");
                statement.setString(2, "Воробьева");
                statement.executeUpdate();
                statement.setString(1, "Черника");
                statement.setString(2, "Соловьева");
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try (ServerSocket serverSocket = new ServerSocket(8010)) {
                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();

                        ClientHandler clientSock = new ClientHandler(clientSocket, conn);

                        System.out.println("Подключен новый клиент");


                        new Thread(clientSock).start();

                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

