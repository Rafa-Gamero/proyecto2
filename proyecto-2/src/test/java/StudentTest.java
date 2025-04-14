import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private Student student;
    private static final String STUDENT_NAME = "John Doe";
    private static final String STUDENT_ADDRESS = "123 Main St";
    private static final String STUDENT_EMAIL = "john.doe@example.com";

    @Before
    public void setUp() {
        student = new Student(STUDENT_NAME, STUDENT_ADDRESS, STUDENT_EMAIL);
    }

    @Test
    public void testConstructor() {
        // Verify that constructor properly sets the values
        assertNotNull("Student ID should not be null", student.getStudentId());
        assertTrue("Student ID should start with 'S'", student.getStudentId().startsWith("S"));
        assertEquals("Name should be set correctly", STUDENT_NAME, student.getName());
        assertEquals("Address should be set correctly", STUDENT_ADDRESS, student.getAddress());
        assertEquals("Email should be set correctly", STUDENT_EMAIL, student.getEmail());
        assertNull("Course should initially be null", student.getCourse());
    }

    @Test
    public void testUniqueIDs() {
        // Create another student and verify they have different IDs
        Student anotherStudent = new Student("Jane Smith", "456 Oak Ave", "jane@example.com");
        assertNotEquals("Different students should have different IDs",
                student.getStudentId(), anotherStudent.getStudentId());
    }

    @Test
    public void testEnrollInCourse() {
        // Create a course
        Course course = new Course("Java Programming", 999.99);
        double initialMoneyEarned = course.getMoneyEarned();

        // Enroll student in course
        student.enrollInCourse(course);

        // Verify that the student is enrolled in the course
        assertEquals("Student should be enrolled in the course", course, student.getCourse());

        // Verify that money earned by course increased by course price
        assertEquals("Course money earned should increase by course price",
                initialMoneyEarned + course.getPrice(), course.getMoneyEarned(), 0.001);
    }

    @Test
    public void testEnrollInNullCourse() {
        // Enroll student in null course (unenroll)
        student.enrollInCourse(null);

        // Verify that the student's course is null
        assertNull("Student's course should be null", student.getCourse());
    }

    @Test
    public void testEnrollInDifferentCourse() {
        // Create two courses
        Course course1 = new Course("Java Programming", 999.99);
        Course course2 = new Course("Data Structures", 1299.99);

        // Enroll student in first course
        student.enrollInCourse(course1);
        double course1MoneyAfterFirstEnrollment = course1.getMoneyEarned();

        // Re-enroll student in second course
        student.enrollInCourse(course2);

        // Verify that student is enrolled in second course
        assertEquals("Student should be enrolled in the second course", course2, student.getCourse());

        // Verify that money earned for each course is correct
        assertEquals("First course money should remain unchanged",
                course1MoneyAfterFirstEnrollment, course1.getMoneyEarned(), 0.001);
        assertEquals("Second course money should increase by course price",
                course2.getPrice(), course2.getMoneyEarned(), 0.001);
    }

    @Test
    public void testToString() {
        // Test toString method when not enrolled in any course
        String representation = student.toString();
        assertTrue("toString should contain student ID", representation.contains(student.getStudentId()));
        assertTrue("toString should contain name", representation.contains(STUDENT_NAME));
        assertTrue("toString should contain address", representation.contains(STUDENT_ADDRESS));
        assertTrue("toString should contain email", representation.contains(STUDENT_EMAIL));
        assertTrue("toString should mention not enrolled", representation.contains("Not enrolled"));

        // Test toString method when enrolled in a course
        Course course = new Course("Python Programming", 799.99);
        student.enrollInCourse(course);
        representation = student.toString();
        assertTrue("toString should contain course name", representation.contains(course.getName()));
        assertFalse("toString should not mention not enrolled", representation.contains("Not enrolled"));
    }
}
