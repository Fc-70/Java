import java.io.*;
import java.util.*;

public class StudentRecordSystem {

    static class Student {
        private String id, name, course;
        private int age;

        public Student(String id, String name, int age, String course) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.course = course;
        }

        public String toFileString() {
            return id + "," + name + "," + age + "," + course;
        }

        public static Student fromFileString(String line) {
            String[] parts = line.split(",");
            return new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
        }

        public String getId() { return id; }

        public void display() {
            System.out.println("ID: " + id +
                               ", Name: " + name +
                               ", Age: " + age +
                               ", Course: " + course);
        }
    }

    static class StudentRecordManager {
        private static final String FILE_NAME = "students.txt";

        public void createFile() throws IOException {
            new File(FILE_NAME).createNewFile();
        }

        public void writeStudent(Student s) throws IOException {
            BufferedWriter w = new BufferedWriter(new FileWriter(FILE_NAME));
            w.write(s.toFileString());
            w.newLine();
            w.close();
        }

        public void appendStudent(Student s) throws IOException {
            BufferedWriter w = new BufferedWriter(new FileWriter(FILE_NAME, true));
            w.write(s.toFileString());
            w.newLine();
            w.close();
        }

        public void readAllStudents() throws IOException {
            BufferedReader r = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = r.readLine()) != null) {
                Student s = Student.fromFileString(line);
                s.display();
            }
            r.close();
        }

        public void updateStudent(String id, Student updated) throws IOException {
            File input = new File(FILE_NAME);
            File temp = new File("students_temp.txt");

            BufferedReader r = new BufferedReader(new FileReader(input));
            PrintWriter w = new PrintWriter(new FileWriter(temp));

            String line;
            while ((line = r.readLine()) != null) {
                Student s = Student.fromFileString(line);
                if (s.getId().equals(id))
                    w.println(updated.toFileString());
                else
                    w.println(line);
            }

            r.close();
            w.close();

            input.delete();
            temp.renameTo(input);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StudentRecordManager m = new StudentRecordManager();
        int choice;

        do {
            System.out.println("\n--- Student Record System ---");
            System.out.println("1. Create record file");
            System.out.println("2. Write student (overwrite)");
            System.out.println("3. Append student");
            System.out.println("4. Read all students");
            System.out.println("5. Update student");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:{
                    m.createFile();
                    System.out.println("Created File Students.txt");
                    break;
                }
                case 2:{
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    int age = readInt(sc, "Enter Age: ");
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    Student s = new Student(id, name, age, course);
                    m.writeStudent(s);
                    break;
                }
                case 3:{
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    int age = readInt(sc, "Enter Age: ");
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    Student s = new Student(id, name, age, course);
                    m.appendStudent(s);
                    break;
                }

                case 4:{
                     m.readAllStudents();
                     break;
                }

                case 5:{
                    System.out.print("Enter ID to update: ");
                    String id = sc.nextLine();
                    System.out.print("Enter new Name: ");
                    String name = sc.nextLine();
                    int age = readInt(sc, "Enter new Age: ");
                    System.out.print("Enter new Course: ");
                    String course = sc.nextLine();

                    Student updated = new Student(id, name, age, course);
                    m.updateStudent(id, updated);
                    break;
                }

                case 0:{
                    System.out.println("Goodbye!");
                    break;
                }
            }

        } while (choice != 0);

        sc.close();
    }

    private static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter an integer: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }
}
