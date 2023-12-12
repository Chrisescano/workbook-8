package org.pluralsight;

import java.sql.*;

public class PetSpaDatabase {
    Connection connection;

    public PetSpaDatabase() {
        String url = "jdbc:mysql://localhost:3306/baibai_petspa";
        String userName = "petspauser";
        String password = "petspauser";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Oops, could not load driver. Make sure POM is properly configured");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Oops, could not create a connection!");
            throw new RuntimeException(e);
        }
    }
}
