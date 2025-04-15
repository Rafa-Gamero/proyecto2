import static org.junit.Assert.*;
import org.junit.Test;

public class TeacherTest {

    @Test
    public void testConstructor() {
        String name = "Laura Sánchez";
        double salary = 4500.0;

        Teacher teacher = new Teacher(name, salary);

        // Verifica que el ID se genere y tenga longitud 8
        assertNotNull("El ID del profesor no debe ser nulo", teacher.getTeacherId());
        assertEquals("El ID debe tener 8 caracteres", 8, teacher.getTeacherId().length());

        // Verifica que el nombre y salario se asignen correctamente
        assertEquals("El nombre debe coincidir", name, teacher.getName());
        assertEquals("El salario debe coincidir", salary, teacher.getSalary(), 0.001);
    }

    @Test
    public void testSetters() {
        Teacher teacher = new Teacher("Ana Torres", 3800.0);

        teacher.setName("Pedro Gómez");
        teacher.setSalary(4200.5);

        assertEquals("Nombre actualizado incorrecto", "Pedro Gómez", teacher.getName());
        assertEquals("Salario actualizado incorrecto", 4200.5, teacher.getSalary(), 0.001);
    }

    @Test
    public void testUniqueIds() {
        Teacher t1 = new Teacher("Carlos", 4000.0);
        Teacher t2 = new Teacher("María", 4000.0);

        assertNotEquals("Los IDs deben ser únicos", t1.getTeacherId(), t2.getTeacherId());
    }

    @Test
    public void testToString() {
        Teacher teacher = new Teacher("Silvia Ramírez", 5000.0);
        String output = teacher.toString();

        assertTrue("toString debe incluir el ID", output.contains(teacher.getTeacherId()));
        assertTrue("toString debe incluir el nombre", output.contains("Silvia Ramírez"));
        assertTrue("toString debe incluir el salario", output.contains("5000.0"));
    }
}
