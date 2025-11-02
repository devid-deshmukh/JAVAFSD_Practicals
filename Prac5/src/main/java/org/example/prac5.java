package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class prac5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV file path: ");
        String filePath = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "pass123"; // change to your MySQL password

        String tableName = "student_data";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String headerLine = br.readLine();
            String[] columns = headerLine.split(",", -1);

            // ✅ Clean special characters in column names
            for (int i = 0; i < columns.length; i++) {
                columns[i] = columns[i].trim()
                        .replace(" ", "_")
                        .replace("/", "_")
                        .replace("-", "_")
                        .replace("\\", "_")
                        .replace("(", "")
                        .replace(")", "");
            }

            // ✅ Drop table if exists
            stmt.executeUpdate("DROP TABLE IF EXISTS " + tableName);

            // ✅ Create table dynamically
            StringBuilder createQuery = new StringBuilder("CREATE TABLE " + tableName + " (");
            for (int i = 0; i < columns.length; i++) {
                createQuery.append("`").append(columns[i]).append("` VARCHAR(255)");
                if (i < columns.length - 1) createQuery.append(", ");
            }
            createQuery.append(")");

            stmt.executeUpdate(createQuery.toString());
            System.out.println("✅ Table created successfully!");

            String row;
            int rowCount = 0;

            // ✅ Insert CSV Rows
            while ((row = br.readLine()) != null) {
                String[] values = row.split(",", -1);

                if (values.length < columns.length) {
                    values = Arrays.copyOf(values, columns.length);
                }
                if (values.length > columns.length) {
                    values = Arrays.copyOf(values, columns.length);
                }

                StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " VALUES(");
                for (int i = 0; i < values.length; i++) {
                    String cleanedValue = values[i].trim().replace("'", "");
                    insertQuery.append("'").append(cleanedValue).append("'");
                    if (i < values.length - 1) insertQuery.append(", ");
                }
                insertQuery.append(")");

                stmt.executeUpdate(insertQuery.toString());
                rowCount++;
            }

            System.out.println("✅ " + rowCount + " rows inserted!");

            // ✅ Show metadata
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData meta = rs.getMetaData();

            int columnCount = meta.getColumnCount();
            System.out.println("\n📌 Number of Columns: " + columnCount);
            System.out.println("📌 Column Names:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("- " + meta.getColumnName(i));
            }

            // ✅ Proper row count
            ResultSet countRS = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
            if (countRS.next()) {
                System.out.println("\n📌 Total Rows in DB: " + countRS.getInt(1));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
