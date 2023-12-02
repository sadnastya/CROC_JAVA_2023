package ru.croc.winter.nastyasad;

import java.sql.Connection;
import java.sql.Statement;

public class DBCreator {
    public final Connection conn;

    public DBCreator(Connection conn) throws Exception {
        this.conn = conn;
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE Candidate(idCandidate INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, surName VARCHAR(255) NOT NULL, party VARCHAR(255));");
        statement.execute("CREATE TABLE Lot(idLot INT AUTO_INCREMENT PRIMARY KEY, idStation INT, ResultOfChecking BOOLEAN)");
        statement.execute("CREATE TABLE Vote(idVote INT AUTO_INCREMENT PRIMARY KEY,  timestamp TIMESTAMP NOT NULL, idLot INT NOT NULL, idCandidate INT NOT NULL, FOREIGN KEY (idLot) REFERENCES Lot(idLot), FOREIGN KEY (idCandidate) REFERENCES Candidate(idCandidate));");
    }
}