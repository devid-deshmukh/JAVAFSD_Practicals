package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class Student {
    String name;
    int totalMarks;

    Student(String name, int totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
    }
}

public class StudentMarks {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/example/student.csv";


        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                // Skip header row
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                String name = data[1];
                int maths = Integer.parseInt(data[5]);
                int physics = Integer.parseInt(data[6]);
                int chemistry = Integer.parseInt(data[7]);

                int total = maths + physics + chemistry;

                students.add(new Student(name, total));
            }

            System.out.println("\n✅ Student Marks Summary\n");
            for (Student s : students) {
                System.out.println(s.name + " → Total Marks: " + s.totalMarks);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
