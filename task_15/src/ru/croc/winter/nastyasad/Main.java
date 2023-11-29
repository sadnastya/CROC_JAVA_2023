package ru.croc.winter.nastyasad;

import java.sql.*;

public class Main {
    public static void main(String[] fileName)
            throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "");
        DBCreator ourDB = new DBCreator(conn);
        ourDB.CSVtoDB("Data_pets.csv");

        Statement statement = ourDB.conn.createStatement();

        ResultSet result =  statement.executeQuery("SELECT * FROM Pet RIGHT JOIN PetToClient ON Pet.idPet = PetToClient.idPet RIGHT JOIN Client ON PetToClient.idClient = Client.idClient;");
        System.out.println("Питомец-хозяин(если хозяев несколько, то и питомец выводится несколько раз): ");
        while (result.next()){
            System.out.println(result.getString(1)+" "+result.getString(2)
                    +" "+result.getString(3)+" "+result.getString(4)+" "+result.getString(7)+" "+result.getString(8)+" "+result.getString(9));
        }
    }
}