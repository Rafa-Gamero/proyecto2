import static org.junit.Assert.*;
import org.junit.Test;

public class TeacherTest {

    @Test
    public void testTeacherCreation() {
        String name = "Juan Pérez";
        double salary = 5000.0;

        Teacher teacher = new Teacher(name, salary);

        assertNotNull(teacher.getTeacherId());
        assertEquals(name, teacher.getName());
        assertEquals(salary, teacher.getSalary(), 0.001);
    }

    @Test
    public void testSetters() {
        Teacher teacher = new Teacher("Juan Pérez", 5000.0);

        String newName = "Pedro González";
        double newSalary = 6000.0;

        teacher.setName(newName);
        teacher.setSalary(newSalary);

        assertEquals(newName, teacher.getName());
        assertEquals(newSalary, teacher.getSalary(), 0.001);
    }

    @Test
    public void testToString() {
        Teacher teacher = new Teacher("Juan Pérez", 5000.0);
        String toString = teacher.toString();

        assertTrue(toString.contains(teacher.getTeacherId()));
        assertTrue(toString.contains("Juan Pérez"));
        // prueba
        assertTrue(toString.contains("5000.0"));
    }
}
