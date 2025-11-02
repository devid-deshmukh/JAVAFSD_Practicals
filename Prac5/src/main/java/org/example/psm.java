package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class psm {
    public static void main(String[] args) {
        try {
            // Step 1: Register Driver (optional in new versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Create Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javafsd",
                    "root",
                    "pass123"
            );

            System.out.println("✅ Connected to DB");

            // ----------------- INSERT -----------------
            PreparedStatement stmt1 = con.prepareStatement("insert into employee values(?,?)");
            stmt1.setInt(1, 101);
            stmt1.setString(2, "Ratan");

            int i1 = stmt1.executeUpdate();
            System.out.println(i1 + " records inserted");

            // ----------------- UPDATE -----------------
            PreparedStatement stmt2 = con.prepareStatement("update employee set name=? where id=?");
            stmt2.setString(1, "Sonoo");
            stmt2.setInt(2, 101);

            int i2 = stmt2.executeUpdate();
            System.out.println(i2 + " records updated");

            // ----------------- DELETE -----------------
            PreparedStatement stmt3 = con.prepareStatement("delete from employee where id=?");
            stmt3.setInt(1, 101);

            int i3 = stmt3.executeUpdate();
            System.out.println(i3 + " records deleted");

            // Step 3: Close connection
            con.close();
            System.out.println("✅ Connection closed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
