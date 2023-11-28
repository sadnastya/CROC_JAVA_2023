package ru.croc.winter.nastyasad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class DBCreator {
    public String fileName;
    public final Connection conn;

    public DBCreator(String fileName, Connection conn) throws Exception {
        this.conn = conn;
        this.fileName = fileName;
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE Client(idClient INT AUTO_INCREMENT PRIMARY KEY,  clientName VARCHAR(255) NOT NULL, clientSurname VARCHAR(255) NOT NULL, phoneNumber VARCHAR(255) NOT NULL UNIQUE);");
        statement.execute("CREATE TABLE Pet(idPet INT AUTO_INCREMENT PRIMARY KEY, petName VARCHAR(255) NOT NULL, petAge INT);");
        statement.execute("CREATE TABLE PetToClient(idPet INT, idClient INT, FOREIGN KEY (idClient) REFERENCES Client(idClient), FOREIGN KEY (idPet) REFERENCES Pet(idPet))");

        CSVtoDB();
    }

    private void CSVtoDB() throws Exception {
        try (BufferedReader r = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] lineToDB = line.split(",");
                String sqlInsertOwner = "INSERT INTO Client (idClient, clientName, clientSurname, phoneNumber) VALUES (?,?,?,?)";
                String sqlInsertPet = "INSERT INTO Pet (idPet, petName, petAge) VALUES (?,?,?)";
                String sqlInsertRef = "INSERT INTO PetToClient (idPet, idClient) VALUES (?,?)";
                try (PreparedStatement statement = conn.prepareStatement(sqlInsertOwner)) {
                    statement.setInt(1, Integer.parseInt(lineToDB[0].split("\"")[1]));
                    statement.setString(2, lineToDB[2]);
                    statement.setString(3, lineToDB[1]);
                    statement.setString(4, lineToDB[3]);
                    statement.executeUpdate();
                } catch (org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException e) {
                } finally {
                    try (PreparedStatement statement = conn.prepareStatement(sqlInsertPet)) {
                        statement.setInt(1, Integer.parseInt(lineToDB[4]));
                        statement.setString(2, lineToDB[5]);
                        statement.setInt(3, Integer.parseInt(lineToDB[6].split("\"")[0]));
                        statement.executeUpdate();
                    } catch (org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException e) {
                    } finally {
                        try (PreparedStatement statement = conn.prepareStatement(sqlInsertRef)) {
                            statement.setInt(2, Integer.parseInt(lineToDB[0].split("\"")[1]));
                            statement.setInt(1, Integer.parseInt(lineToDB[4]));
                            statement.executeUpdate();
                        } catch (org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException e) {
                        }
                    }
                }
            }
        }
    }


}
