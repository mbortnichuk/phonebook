package com.mbortnichuk.phonebook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariana on 05-Apr-17.
 */
public class DatabasePersistance implements Persistance {

    private Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "pass");

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> read(String key, String value) {
        Connection connect = null;
        try {
            connect = getConnection();
            String sql = "SELECT * FROM test.phonebook WHERE " + key + " = '" + value + "' ";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql); //executeupdate

            List<Record> recordList = new ArrayList<>();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String phonenumber = resultSet.getString("phone_number");
                String name = resultSet.getString("name");

                Record record = new Record(id, phonenumber, name);
                recordList.add(record);
            }
            return recordList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connect);
        }
        return null;
    }

    @Override
    public List<Record> readALL() {
        Connection connect = null;
        try {
            connect = getConnection();
            String sql = "SELECT * FROM test.phonebook;";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Record> recordList = new ArrayList<>();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String phonenumber = resultSet.getString("phone_number");
                String name = resultSet.getString("name");

                Record record = new Record(id, phonenumber, name);
                recordList.add(record);
            }
            return recordList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connect);
        }
        return null;
    }

    @Override
    public void create(Record record) {
        Connection connect = null;
        try {
            connect = getConnection();
            String sql = "INSERT INTO test.phonebook (id, phone_number, name) VALUES " + " ('" + record.getId() + "', '" + record.getPhone() + "', '" + record.getName() + "') ";
            Statement statement = connect.createStatement();
            int resultSet = statement.executeUpdate(sql); //executeupdate

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connect);
        }
    }

    @Override
    public int update(Record record, String key, String value) {
        Connection connect = null;
        try {
            connect = getConnection();
            String sql = "UPDATE test.phonebook SET " + " phone_number='" + record.getPhone() + "', name='" + record.getName() + "' " + "WHERE " + key + " = '" + value + "' ";
            Statement statement = connect.createStatement();
            int rowsAffected = statement.executeUpdate(sql); //executeupdate
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connect);
        }
        return 0;
    }


    @Override
    public int delete(String key, String value) {
        Connection connect = null;
        try {
            connect = getConnection();
            String sql = "DELETE FROM test.phonebook WHERE " + key + " = '" + value + "' ";
            Statement statement = connect.createStatement();
            int rowsAffected = statement.executeUpdate(sql); //executeupdate
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connect);
        }
        return 0;
    }

    public static void main(String[] args) {

        DatabasePersistance dbp = new DatabasePersistance();
//        List<Record> result = dbp.read("phone_number", "099");
//        System.out.println(result);
//
//        List<Record> result2 = dbp.readALL();
//        System.out.println(result2);

        Record record = new Record(2, "070", "Vasia");
//       dbp.create(record);

      //  dbp.delete("phone_number", "080");

        System.out.println(dbp.update(record, "phone_number", "070"));

    }

}
