import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    public class CourseTest {

        private Course curso;
        private final String NOMBRE_CURSO = "Programación Java";
        private final double PRECIO_CURSO = 1500.0;

        @BeforeEach
        public void setUp() {
            curso = new Course(NOMBRE_CURSO, PRECIO_CURSO);
        }

        @Test
        public void testConstructor() {
            assertNotNull(curso.getCourseId());
            assertEquals(NOMBRE_CURSO, curso.getName());
            assertEquals(PRECIO_CURSO, curso.getPrice());
            assertEquals(0.0, curso.getMoney_earned());
            assertNull(curso.getTeacher());
        }

        @Test
        public void testUpdateMoneyEarned() {
            // inicialmente el dinero ganado es 0
            assertEquals(0.0, curso.getMoney_earned());

            // Actualizacion de dinero ganado
            curso.updateMoneyEarned();

            // Verificar dinero ganado sea igual al precio del curso
            assertEquals(PRECIO_CURSO, curso.getMoney_earned());

            // Actualizar otra vez dinero ganado
            curso.updateMoneyEarned();

            // Comprobacion de que se haya acumulado correctamente
            assertEquals(PRECIO_CURSO * 2, curso.getMoney_earned());
        }

        @Test
        public void testToString() {
            String expectedString = "Curso [ID: " + curso.getCourseId() +
                    ", Nombre: " + NOMBRE_CURSO +
                    ", Precio: " + PRECIO_CURSO +
                    ", Dinero ganado: 0.0" +
                    ", Profesor: No asignado]";
            assertEquals(expectedString, curso.toString());

            // Asignar un profesor y verificar que el toString se actualice
            Teacher teacher = new Teacher("Juan Pérez", 2500.0);
            curso.setTeacher(teacher);

            expectedString = "Curso [ID: " + curso.getCourseId() +
                    ", Nombre: " + NOMBRE_CURSO +
                    ", Precio: " + PRECIO_CURSO +
                    ", Dinero ganado: 0.0" +
                    ", Profesor: Juan Pérez]";
            assertEquals(expectedString, curso.toString());
        }
    }
