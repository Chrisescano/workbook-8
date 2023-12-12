package org.pluralsight;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PetDataManager {
    private final Connection connection;

    public PetDataManager(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM pet");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                LocalDate tmpDate = null;
                if (resultSet.getDate("birthday") != null) {
                    tmpDate = resultSet.getDate("birthday").toLocalDate();
                }

                pets.add(new Pet(
                        resultSet.getInt("pet_id"),
                        resultSet.getInt("owner_id"),
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        tmpDate
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pets;
    }

    public void updatePet(Pet pet) {
        try {
            PreparedStatement ps = connection.prepareStatement("""
                    UPDATE pet
                    SET name = ?, species = ?, birthday = ?
                    WHERE pet_id = ?;
                    """);
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getSpecies());
            ps.setDate(3, java.sql.Date.valueOf(pet.getBirthday()));
            ps.setInt(4, pet.getPetID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPet(Pet pet) {
        try {
            PreparedStatement ps = connection.prepareStatement("""
                    INSERT INTO pet (`name`, species, birthday, owner_id)
                    VALUES (?, ?, ?, ?);
                    """);
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getSpecies());
            ps.setDate(3, java.sql.Date.valueOf(pet.getBirthday()));
            ps.setInt(4, pet.getOwnerID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
