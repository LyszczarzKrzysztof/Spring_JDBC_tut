package org.example;

import java.sql.*;

public class MainJDBC {

    public static final String USER = "tutorial";
    public static final String PASSWORD = "password";
    public static final String URL = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from products");

            while (resultSet.next()){
                String productName = resultSet.getString("productName");
                double buyPrice = resultSet.getDouble("buyPrice");

                System.out.println(productName+": "+buyPrice);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
