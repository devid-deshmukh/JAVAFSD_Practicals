package org.example;
import java.io.*;
public class ByteCount{
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("bytefile.dat");
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(95);
            dos.writeUTF("Java File Handling");
            dos.writeDouble(85.50);

            System.out.println("✅ Total bytes written: " + dos.size());

            dos.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e);
        }
    }
}
