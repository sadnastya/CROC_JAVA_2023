package ru.croc.winter.nastyasad.dao;

import ru.croc.winter.nastyasad.pojo.Client;
import ru.croc.winter.nastyasad.pojo.Pet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PetDAO {
    protected final Connection conn;

    public PetDAO(Connection conn) {
        this.conn = conn;

    }

    public void createPet(String name, Integer age, List<Client> clients) {
        String sqlInsertPet = "INSERT INTO Pet (petName, petAge) VALUES (?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sqlInsertPet)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Client client: clients){
            String sqlInsertRelation = "INSERT INTO PetToClient (idPet, idClient) VALUES (?,?)";
            try (PreparedStatement statement = conn.prepareStatement(sqlInsertRelation)) {
                Statement st = conn.createStatement();
                ResultSet res = st.executeQuery("SELECT * FROM Pet ORDER BY idPet DESC LIMIT 1;");
                res.next();
                statement.setInt(1, res.getInt(1));
                statement.setInt(2, client.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Pet findPet(Integer medicalCardNumber) throws SQLException {
        String getPet = "SELECT * FROM Pet WHERE idPet = ?";
        ResultSet petDB;
        try (PreparedStatement st = conn.prepareStatement(getPet)) {
            st.setInt(1, medicalCardNumber);
            petDB = st.executeQuery();
            petDB.next();
            return new Pet(medicalCardNumber, petDB.getString(2), petDB.getInt(3));
        }

    }

    public Pet updatePet(Pet pet) throws SQLException {
        String sql = "UPDATE Pet SET petName = ?, petAge = ? WHERE idPet = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(3, pet.getMedCard());
            statement.setString(1, pet.getName());
            statement.setInt(2, pet.getAge());
            if (statement.executeUpdate() > 0)
                return pet;
            else
                return null;
        }
    }

    public void deletePet(Integer medicalCardNumber) throws SQLException {
        String delPet = "DELETE FROM Pet WHERE idPet = ?";

        String delRelation = "DELETE FROM PetToClient WHERE idPet = ?";
        try (PreparedStatement statement = conn.prepareStatement(delPet)) {
            try (PreparedStatement st = conn.prepareStatement(delRelation)) {
                st.setInt(1, medicalCardNumber);
                st.executeUpdate();
            }
            statement.setInt(1, medicalCardNumber);
            statement.executeUpdate();
        }
    }

    public List<Pet> getAllPetsOf(Client client) throws SQLException {
        List<Pet> allPets = new ArrayList<Pet>();

        String findPets = "SELECT idPet FROM PetToClient WHERE idClient = ?";

        try (PreparedStatement stant = conn.prepareStatement(findPets)) {
            stant.setInt(1, client.getId());
            ResultSet petsIdForCheck = stant.executeQuery();
            while (petsIdForCheck.next()) {
                allPets.add(findPet(petsIdForCheck.getInt(1)));
            }
        }

        return allPets;
    }
}
