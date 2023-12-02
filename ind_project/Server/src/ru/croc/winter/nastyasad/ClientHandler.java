package ru.croc.winter.nastyasad;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.sql.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private final Connection conn;
    private String stationID;
    public ClientHandler(Socket socket, Connection conn) {
        this.conn=conn;
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.println("2"+clientSocket.isClosed());
        try (ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            List<Vote> receivedVotes = (List<Vote>) inputStream.readObject();
            try (InputStream input = clientSocket.getInputStream();
                 BufferedReader inn = new BufferedReader(new InputStreamReader(input))){
                stationID = inn.readLine();
                out.println("Результат проверки на вброс: "+saveToBDAndHandle(receivedVotes));

            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized boolean saveToBDAndHandle(List<Vote> votes){
        System.out.println("1"+clientSocket.isClosed());
        String insertLot = "INSERT INTO Lot (idStation) VALUES (?)";
        try (PreparedStatement statement = conn.prepareStatement(insertLot)) {
            statement.setInt(1, Integer.parseInt(stationID));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int lot;
        try (Statement statement1 = conn.createStatement()) {
            ResultSet lotNumber = statement1.executeQuery("SELECT * FROM Lot ORDER BY idLot DESC LIMIT 1;");
            lotNumber.next();
            lot = lotNumber.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String insertVotes = "INSERT INTO Vote (timestamp,idLot,idCandidate) VALUES (?,?,?)";
        for (Vote vote : votes) {
            try (PreparedStatement statement = conn.prepareStatement(insertVotes)) {

                statement.setTimestamp(1, vote.getTimestamp());
                statement.setInt(2, lot);
                statement.setInt(3, vote.getCandidateId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        StatisticalModule stat = new StatisticalModule(conn);
        stat.readResultOfChecking();
        stat.findProbabilityBD();
        return stat.isBallotStuffing(votes);

    }
}