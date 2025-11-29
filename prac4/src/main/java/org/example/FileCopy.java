package org.example;
import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        try {
            // Source file (must already exist)
            BufferedReader br = new BufferedReader(new FileReader("source.txt"));

            // Destination file (created automatically)
            BufferedWriter bw = new BufferedWriter(new FileWriter("destination.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine(); // move to next line
            }

            br.close();
            bw.close();

            System.out.println("✅ File copied successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e);
        }
    }
}
