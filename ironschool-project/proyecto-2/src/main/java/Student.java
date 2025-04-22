import java.util.UUID;

public class Student {
    // Private member variables
    private String studentId;
    private String name;
    private String address;
    private String email;
    private Course course;  // Nullable - represents the course this student is enrolled in

    // Static counter for auto-generating IDs
    private static int idCounter = 1000;

    /**
     * Parameterized constructor that takes name, address and email
     */
    public Student(String name, String address, String email) {
        this.studentId = "S" + idCounter++;  // Auto-generate ID with prefix 'S'
        this.name = name;
        this.address = address;
        this.email = email;
        this.course = null;  // Initially, student is not enrolled in any course
    }

    // Getter methods
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Course getCourse() {
        return course;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Enrolls this student in a course
     * @param course The course to enroll in
     */
    public void enrollInCourse(Course course) {
        this.course = course;
        if (course != null) {
            // Update the money earned by this course based on its price
            course.setMoney_earned(course.getMoney_earned() + course.getPrice());
        }
    }

    /**
     * Returns a string representation of the student
     */
    @Override
    public String toString() {
        String courseInfo = (course != null) ? course.getName() : "Not enrolled";
        return "Student ID: " + studentId +
                "\nName: " + name +
                "\nAddress: " + address +
                "\nEmail: " + email +
                "\nCourse: " + courseInfo;
    }
}

