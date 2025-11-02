package org.example;

import java.sql.*;

public class prac5 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 2: Create a connection
        String url = "jdbc:mysql://localhost:3306/javafsd";
        String username = "root";
        String password = "pass123";  // change this
        Connection connection = DriverManager.getConnection(url, username, password);

        // step 3: create statement object
        Statement statement = connection.createStatement();

        // step 4: Execute query (READ)
        String sql = "SELECT * FROM employee";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println("Id: " + resultSet.getInt(1) + ", Name: " + resultSet.getString(2));
        }

        // INSERT operation
        String insertQuery = "INSERT INTO employee (id, name) VALUES (101, 'Ratan')";
        int insertCount = statement.executeUpdate(insertQuery);
        System.out.println("(" + insertCount + ") row inserted");

        // UPDATE operation
        String updateQuery = "UPDATE employee SET name='Sonoo' WHERE id=101";
        int updateCount = statement.executeUpdate(updateQuery);
        System.out.println("(" + updateCount + ") row updated");

        // DELETE operation
        String deleteQuery = "DELETE FROM employee WHERE id=101";
        int deleteCount = statement.executeUpdate(deleteQuery);
        System.out.println("(" + deleteCount + ") row deleted");

        // step 5: close connection
        connection.close();
    }
}
