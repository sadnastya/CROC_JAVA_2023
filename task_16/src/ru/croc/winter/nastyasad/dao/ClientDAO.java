package ru.croc.winter.nastyasad.dao;

import ru.croc.winter.nastyasad.ClientAlreadyExistsException;
import ru.croc.winter.nastyasad.pojo.Client;
import ru.croc.winter.nastyasad.pojo.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends PetDAO {
    public ClientDAO(Connection conn) {
        super(conn);
    }

    public void createClient(Client client) throws ClientAlreadyExistsException {
        String sqlInsertPet = "INSERT INTO Client (clientName, clientSurname, phoneNumber) VALUES (?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sqlInsertPet)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getPhoneNumber());
            statement.executeUpdate();
        } catch (org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException e) {
            throw new ClientAlreadyExistsException();
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public Client findClient(Integer id) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM Client WHERE idClient=?;")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    String name = result.getString("clientName");
                    String surname = result.getString("clientSurname");
                    String phone = result.getString("phoneNumber");
                    return new Client(id, name, surname, phone);
                } else {
                    return null;
                }
            }
        }
    }

    public List<Client> getAllClientsOf(Pet pet) throws SQLException {
        List<Client> allClients = new ArrayList<Client>();

        String findPets = "SELECT idClient FROM PetToClient WHERE idPet = ?";

        try (PreparedStatement stant = conn.prepareStatement(findPets)) {
            stant.setInt(1, pet.getMedCard());
            ResultSet clients = stant.executeQuery();
            while (clients.next()) {
                allClients.add(findClient(clients.getInt(1)));
            }
        }

        return allClients;
    }

    public Client updateClient(Client client) throws SQLException {
        String sql = "UPDATE Client SET clientName = ?, clientSurname = ?, phoneNumber = ? WHERE idClient = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getPhoneNumber());
            statement.setInt(4, client.getId());
            if (statement.executeUpdate() > 0)
                return client;
            else
                return null;
        }
    }

    public void deleteClient(Integer id) throws SQLException {
        String delClient = "DELETE FROM Client WHERE idClient = ?";

        String delRelation = "DELETE FROM PetToClient WHERE idClient = ?";

        //проверяем, если у питомца(-ев) не останется хозяев, то его тоже удаляем, руководствуясь логикой, что клиент без питомца может быть, а питомец без клиента не может быть
        List<Pet> petsForCheck = getAllPetsOf(findClient(id));
        for (Pet pet : petsForCheck) {
            List<Client> owners = getAllClientsOf(pet);
            if (owners.size() < 2) {
                deletePet(pet.getMedCard());
            }
        }

        try (PreparedStatement statement = conn.prepareStatement(delClient)) {
            try (PreparedStatement st = conn.prepareStatement(delRelation)) {
                st.setInt(1, id);
                st.executeUpdate();
            }
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<String> findClientPhoneNumbersBy(Pet pet) throws SQLException {
        List<Client> clients = getAllClientsOf(pet);
        List<String> phoneNumbers = new ArrayList<>();
        for(Client client: clients){
            phoneNumbers.add(client.getPhoneNumber());
        }
        return phoneNumbers;
    }
}
