import java.util.*;
// aplicacion final


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

        while (true) {
            showMenu();
            System.out.print("Choose an option (0 to exit): ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.println("Enroll a student into a course");
                    enrollOption();
                    break;
                case "2":
                    System.out.println("Assign a teacher to a course");
                    assignOption();
                    break;
                case "3":
                    System.out.println("All courses:");
                    showCourses();
                    break;
                case "4":
                    System.out.print("Enter Course ID: ");
                    lookupCourse(scanner.nextLine());
                    break;
                case "5":
                    System.out.println("All students:");
                    showStudents();
                    break;
                case "6":
                    System.out.print("Enter Student ID: ");
                    lookupStudent(scanner.nextLine());
                    break;
                case "7":
                    System.out.println("All teachers:");
                    showTeachers();
                    break;
                case "8":
                    System.out.print("Enter Teacher ID: ");
                    lookupTeacher(scanner.nextLine());
                    break;
                case "9":
                    showProfit();
                    break;
                case "0":
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a number from 0 to 9.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== SCHOOL MENU =====");
        System.out.println("1. Enroll a student into a course");
        System.out.println("2. Assign a teacher to a course");
        System.out.println("3. Show all courses");
        System.out.println("4. Show details of a specific course");
        System.out.println("5. Show all students");
        System.out.println("6. Show details of a specific student");
        System.out.println("7. Show all teachers");
        System.out.println("8. Show details of a specific teacher");
        System.out.println("9. Show total profit");
        System.out.println("0. Exit");
    }

    private static void enrollOption() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        enrollStudent(studentId, courseId);
    }

    private static void assignOption() {
        System.out.print("Enter Teacher ID: ");
        String teacherId = scanner.nextLine();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        assignTeacher(teacherId, courseId);
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
