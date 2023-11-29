package ru.croc.winter.nastyasad;

import ru.croc.winter.nastyasad.dao.ClientDAO;
import ru.croc.winter.nastyasad.dao.PetDAO;
import ru.croc.winter.nastyasad.pojo.Client;
import ru.croc.winter.nastyasad.pojo.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] a)
            throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "");
        DBCreator ourDB = new DBCreator(conn);
        ourDB.CSVtoDB("Data_pets.csv");

        Statement statement = ourDB.conn.createStatement();

        ClientDAO daoClient = new ClientDAO(conn);
        PetDAO daoPet = new PetDAO(conn);

        Client newClient = new Client(-1, "Виктор", "Выборов", "89999999999");
        daoClient.createClient(newClient);

        System.out.println("Добавили нового клиента в таблицу:");
        ResultSet newResult1 = statement.executeQuery("SELECT * FROM Client;");
        while (newResult1.next()) {
            System.out.println(newResult1.getString(1) + " " + newResult1.getString(2)
                    + " " + newResult1.getString(3) + " " + newResult1.getString(4));
        }


        System.out.println();
        System.out.println("Ищем клиента с id = 3");
        System.out.println(daoClient.findClient(3));

        System.out.println();
        System.out.println("Изменили клиента с id=5");
        System.out.println(daoClient.updateClient(new Client(5, "Екатерина", "Акимова", "8799999999")));

        System.out.println();
        System.out.println("Удалили клиента с id = 6(при этом удалили питомцев, которые остались без хозяев):");
        daoClient.deleteClient(6);
        ResultSet newResult3 = statement.executeQuery("SELECT * FROM Pet;");
        while (newResult3.next()) {
            System.out.println(newResult3.getString(1) + " " + newResult3.getString(2)
                    + " " + newResult3.getString(3) );
        }

        System.out.println();
        ResultSet newResult2 = statement.executeQuery("SELECT * FROM Client;");
        while (newResult2.next()) {
            System.out.println(newResult2.getString(1) + " " + newResult2.getString(2)
                    + " " + newResult2.getString(3) + " " + newResult2.getString(4));
        }

        System.out.println();
        System.out.println("Добавили питомца: ");
        List<Client> ownersToNewPet= new ArrayList<>();
        ownersToNewPet.add(daoClient.findClient(13));
        daoPet.createPet("Листик", 1, ownersToNewPet);
        ResultSet newResult4 = statement.executeQuery("SELECT * FROM Pet;");
        while (newResult4.next()) {
            System.out.println(newResult4.getString(1) + " " + newResult4.getString(2)
                    + " " + newResult4.getString(3) );
        }

        System.out.println();
        System.out.println("Хозяева питомца с id = 13");
        System.out.println(daoClient.getAllClientsOf(daoPet.findPet(14)));

        System.out.println();
        System.out.println("Ищем питомца с id = 2");
        System.out.println(daoPet.findPet(2));

        System.out.println();
        System.out.println("Обновили питомца с id = 2");
        System.out.println(daoPet.updatePet(new Pet(2, "Смешок", 4)));

        System.out.println();
        System.out.println("Получаем питомцев клиента с  id = 10");
        System.out.println(daoPet.getAllPetsOf(daoClient.findClient(10)));

        System.out.println();
        System.out.println("Ищем номера телефонов хозяев питомца с id = 1");
        System.out.println(daoClient.findClientPhoneNumbersBy(daoPet.findPet(1)));
    }
}