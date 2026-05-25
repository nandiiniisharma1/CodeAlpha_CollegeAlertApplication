import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        ArrayList<Double> grades;

        Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        void addGrade(double grade) {
            grades.add(grade);
        }

        double getAverage() {
            if (grades.isEmpty()) return 0;
            double sum = 0;
            for (double g : grades) sum += g;
            return sum / grades.size();
        }

        double getHighest() {
            if (grades.isEmpty()) return 0;
            double max = grades.get(0);
            for (double g : grades) if (g > max) max = g;
            return max;
        }

        double getLowest() {
            if (grades.isEmpty()) return 0;
            double min = grades.get(0);
            for (double g : grades) if (g < min) min = g;
            return min;
        }

        String getLetterGrade() {
            double avg = getAverage();
            if (avg >= 90) return "A";
            if (avg >= 80) return "B";
            if (avg >= 70) return "C";
            if (avg >= 60) return "D";
            return "F";
        }
    }

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrade();
                case 3 -> viewStudent();
                case 4 -> printReport();
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Exiting. Goodbye.");
        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n--- Student Grade Tracker ---");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View Student Details");
        System.out.println("4. View Summary Report");
        System.out.println("5. Exit");
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.println("Student already exists.");
                return;
            }
        }
        students.add(new Student(name));
        System.out.println("Student \"" + name + "\" added.");
    }

    static void addGrade() {
        if (students.isEmpty()) {
            System.out.println("No students found. Add a student first.");
            return;
        }
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        Student student = findStudent(name);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        double grade = getDoubleInput("Enter grade (0 - 100): ");
        if (grade < 0 || grade > 100) {
            System.out.println("Grade must be between 0 and 100.");
            return;
        }
        student.addGrade(grade);
        System.out.println("Grade added for " + student.name + ".");
    }

    static void viewStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        Student student = findStudent(name);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("\nName    : " + student.name);
        System.out.println("Grades  : " + student.grades);
        System.out.printf("Average : %.2f%n", student.getAverage());
        System.out.printf("Highest : %.2f%n", student.getHighest());
        System.out.printf("Lowest  : %.2f%n", student.getLowest());
        System.out.println("Grade   : " + student.getLetterGrade());
    }

    static void printReport() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\n========== Summary Report ==========");
        System.out.printf("%-20s %-10s %-10s %-10s %-8s%n",
                "Name", "Average", "Highest", "Lowest", "Grade");
        System.out.println("----------------------------------------------------");
        for (Student s : students) {
            if (s.grades.isEmpty()) {
                System.out.printf("%-20s %-10s %-10s %-10s %-8s%n",
                        s.name, "N/A", "N/A", "N/A", "N/A");
            } else {
                System.out.printf("%-20s %-10.2f %-10.2f %-10.2f %-8s%n",
                        s.name, s.getAverage(), s.getHighest(), s.getLowest(), s.getLetterGrade());
            }
        }
        System.out.println("====================================================");
    }

    static Student findStudent(String name) {
        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }
}
