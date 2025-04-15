import java.util.*;

public class SchoolApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Teacher> teachers = new HashMap<>();
    private static final Map<String, Course> courses = new HashMap<>();
    private static final Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        System.out.print("Enter school name: ");
        String schoolName = scanner.nextLine();
        System.out.println("Welcome to " + schoolName + " School Management System!");

        createTeachers();
        createCourses();
        createStudents();

        String command;
        System.out.println("\nEnter commands (type EXIT to quit):");
        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("EXIT")) break;
            handleCommand(command);
        }
    }

    private static void createTeachers() {
        System.out.print("How many teachers? ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.print("Enter teacher name: ");
            String name = scanner.nextLine();
            System.out.print("Enter teacher salary: ");
            double salary = Double.parseDouble(scanner.nextLine());
            Teacher t = new Teacher(name, salary);
            teachers.put(t.getTeacherId(), t);
            System.out.println("Created: " + t);
        }
    }

    private static void createCourses() {
        System.out.print("How many courses? ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.print("Enter course name: ");
            String name = scanner.nextLine();
            System.out.print("Enter course price: ");
            double price = Double.parseDouble(scanner.nextLine());
            Course c = new Course(name, price);
            courses.put(c.getCourseId(), c);
            System.out.println("Created: " + c);
        }
    }

    private static void createStudents() {
        System.out.print("How many students? ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student address: ");
            String address = scanner.nextLine();
            System.out.print("Enter student email: ");
            String email = scanner.nextLine();
            Student s = new Student(name, address, email);
            students.put(s.getStudentId(), s);
            System.out.println("Created: " + s);
        }
    }

    private static void handleCommand(String input) {
        try {
            String[] tokens = input.split(" ");
            switch (tokens[0].toUpperCase()) {
                case "ENROLL":
                    enrollStudent(tokens[1], tokens[2]);
                    break;
                case "ASSIGN":
                    assignTeacher(tokens[1], tokens[2]);
                    break;
                case "SHOW":
                    switch (tokens[1].toUpperCase()) {
                        case "COURSES": showCourses(); break;
                        case "STUDENTS": showStudents(); break;
                        case "TEACHERS": showTeachers(); break;
                        case "PROFIT": showProfit(); break;
                    }
                    break;
                case "LOOKUP":
                    switch (tokens[1].toUpperCase()) {
                        case "COURSE": lookupCourse(tokens[2]); break;
                        case "STUDENT": lookupStudent(tokens[2]); break;
                        case "TEACHER": lookupTeacher(tokens[2]); break;
                    }
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void enrollStudent(String studentId, String courseId) {
        Student s = students.get(studentId);
        Course c = courses.get(courseId);
        if (s != null && c != null) {
            s.setCourse(c);
            c.enrollStudent();
            System.out.println("Student enrolled.");
        } else {
            System.out.println("Invalid student or course ID.");
        }
    }

    private static void assignTeacher(String teacherId, String courseId) {
        Teacher t = teachers.get(teacherId);
        Course c = courses.get(courseId);
        if (t != null && c != null) {
            c.setTeacher(t);
            System.out.println("Teacher assigned to course.");
        } else {
            System.out.println("Invalid teacher or course ID.");
        }
    }

    private static void showCourses() {
        courses.values().forEach(System.out::println);
    }

    private static void showStudents() {
        students.values().forEach(System.out::println);
    }

    private static void showTeachers() {
        teachers.values().forEach(System.out::println);
    }

    private static void lookupCourse(String courseId) {
        Course c = courses.get(courseId);
        System.out.println(c != null ? c : "Course not found.");
    }

    private static void lookupStudent(String studentId) {
        Student s = students.get(studentId);
        System.out.println(s != null ? s : "Student not found.");
    }

    private static void lookupTeacher(String teacherId) {
        Teacher t = teachers.get(teacherId);
        System.out.println(t != null ? t : "Teacher not found.");
    }

    private static void showProfit() {
        double totalEarned = courses.values().stream().mapToDouble(Course::getMoney_earned).sum();
        double totalSalaries = teachers.values().stream().mapToDouble(Teacher::getSalary).sum();
        double profit = totalEarned - totalSalaries;
        System.out.println("Total Profit: " + profit);
    }
}
