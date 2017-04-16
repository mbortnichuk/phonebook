package com.mbortnichuk.phonebook;

import java.sql.*;

/**
 * Created by Mariana on 05-Apr-17.
 */
public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "pass");

        String sql = "SELECT * FROM test.phonebook";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            int id = result.getInt("id");
            String phonenumber = result.getString("phone_number");
            String name = result.getString("name");

            System.out.println("id: " + id);
            System.out.println("phone_number: " + phonenumber);
            System.out.println("name: " + name);

        }
        result.close();
        statement.close();
        connection.close();
    }
}
