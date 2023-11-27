package ru.croc.winter.nastyasad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class CreateDB {
    public String fileName;
    public Connection conn = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "");

    public CreateDB(String fileName) throws Exception {
        this.fileName = fileName;
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE Owner(idOwner INT PRIMARY KEY, ownerName VARCHAR(255) NOT NULL, ownerSurname VARCHAR(255) NOT NULL, phoneNumber VARCHAR(255) NOT NULL);");
        statement.execute("CREATE TABLE Pet(idPet INT PRIMARY KEY, petName VARCHAR(255) NOT NULL, petAge INT);");
        statement.execute("CREATE TABLE PetToOwner(idPet INT, idOwner INT, FOREIGN KEY (idOwner) REFERENCES Owner(idOwner), FOREIGN KEY (idPet) REFERENCES Pet(idPet))");
        CSVtoDB();
    }

    private void CSVtoDB() throws Exception {
        try (BufferedReader r = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] lineToDB = line.split(",");
                String sqlInsertOwner = "INSERT INTO Owner (idOwner, ownerName, ownerSurname, phoneNumber) VALUES (?,?,?,?)";
                String sqlInsertPet = "INSERT INTO Pet (idPet, petName, petAge) VALUES (?,?,?)";
                String sqlInsertRef = "INSERT INTO PetToOwner (idPet, idOwner) VALUES (?,?)";
                try (PreparedStatement statement = conn.prepareStatement(sqlInsertOwner)) {
                    statement.setInt(1, Integer.parseInt(lineToDB[0].split("\"")[1]));
                    statement.setString(2, lineToDB[1]);
                    statement.setString(3, lineToDB[2]);
                    statement.setString(4, lineToDB[3]);
                    statement.executeUpdate();
                } catch (SQLException ignored) {}
                finally {
                    try (PreparedStatement statement = conn.prepareStatement(sqlInsertPet)) {
                        statement.setInt(1, Integer.parseInt(lineToDB[4]));
                        statement.setString(2, lineToDB[5]);
                        statement.setInt(3, Integer.parseInt(lineToDB[6].split("\"")[0]));
                        statement.executeUpdate();
                    } catch (SQLException ignored) {}
                    finally {
                        try (PreparedStatement statement = conn.prepareStatement(sqlInsertRef)) {
                            statement.setInt(2, Integer.parseInt(lineToDB[0].split("\"")[1]));
                            statement.setInt(1, Integer.parseInt(lineToDB[4]));
                            statement.executeUpdate();
                        } catch (SQLException ignored) {}
                    }
                }
            }
        }
    }



}
