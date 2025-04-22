import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student student;
    private static final String STUDENT_NAME = "John Doe";
    private static final String STUDENT_ADDRESS = "123 Main St";
    private static final String STUDENT_EMAIL = "john.doe@example.com";

    @BeforeEach
    public void setUp() {
        student = new Student(STUDENT_NAME, STUDENT_ADDRESS, STUDENT_EMAIL);
    }

    @Test
    public void testConstructor() {
        assertNotNull(student.getStudentId(), "Student ID should not be null");
        assertEquals(STUDENT_NAME, student.getName(), "Name should be set correctly");
        assertEquals(STUDENT_ADDRESS, student.getAddress(), "Address should be set correctly");
        assertEquals(STUDENT_EMAIL, student.getEmail(), "Email should be set correctly");
        assertNull(student.getCourse(), "Course should initially be null");
    }

    @Test
    public void testUniqueIDs() {
        Student anotherStudent = new Student("Jane Smith", "456 Oak Ave", "jane@example.com");
        assertNotEquals(student.getStudentId(), anotherStudent.getStudentId(),
                "Different students should have different IDs");
    }

    @Test
    public void testEnrollInCourse() {
        Course course = new Course("Java Programming", 999.99);
        double initialMoneyEarned = course.getMoney_earned();

        student.enrollInCourse(course);

        assertEquals(course, student.getCourse(), "Student should be enrolled in the course");
        assertEquals(initialMoneyEarned + course.getPrice(), course.getMoney_earned(), 0.001,
                "Course money earned should increase by course price");
    }

    @Test
    public void testEnrollInNullCourse() {
        student.enrollInCourse(null);
        assertNull(student.getCourse(), "Student's course should be null");
    }

    @Test
    public void testEnrollInDifferentCourse() {
        Course course1 = new Course("Java Programming", 999.99);
        Course course2 = new Course("Data Structures", 1299.99);

        student.enrollInCourse(course1);
        double course1MoneyAfterFirstEnrollment = course1.getMoney_earned();

        student.enrollInCourse(course2);

        assertEquals(course2, student.getCourse(), "Student should be enrolled in the second course");
        assertEquals(course1MoneyAfterFirstEnrollment, course1.getMoney_earned(), 0.001,
                "First course money should remain unchanged");
        assertEquals(course2.getPrice(), course2.getMoney_earned(), 0.001,
                "Second course money should increase by course price");
    }

    @Test
    public void testToString() {
        String representation = student.toString();
        assertTrue(representation.contains(student.getStudentId()));
        assertTrue(representation.contains(STUDENT_NAME));
        assertTrue(representation.contains(STUDENT_ADDRESS));
        assertTrue(representation.contains(STUDENT_EMAIL));
        assertTrue(representation.contains("Not enrolled"));

        Course course = new Course("Python Programming", 799.99);
        student.enrollInCourse(course);
        representation = student.toString();
        assertTrue(representation.contains(course.getName()));
        assertFalse(representation.contains("Not enrolled"));
    }
}

