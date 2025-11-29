package org.example;
import java.io.*;

public class Append {
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("sample.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Hello , this is Devid Deshmukh..a full stack web developer.\n");
            bw.close();

            System.out.println("✅ Text appended successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e);
        }
    }
}
