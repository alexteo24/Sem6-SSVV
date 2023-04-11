import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

public class TestAddGrade {
    private StudentValidator studentValidator = new StudentValidator();
    private NotaValidator notaValidator = new NotaValidator();
    private TemaValidator temaValidator = new TemaValidator();

    private StudentXMLRepository studentXMLRepo = new StudentXMLRepository(studentValidator, "./src/test/java/studenti.xml");
    private NotaXMLRepository notaXMLRepo = new NotaXMLRepository(notaValidator, "./src/test/java/note.xml");
    private TemaXMLRepository temaXMLRepo = new TemaXMLRepository(temaValidator, "./src/test/java/teme.xml");

    private Service service = new Service(studentXMLRepo, temaXMLRepo, notaXMLRepo);

    @Test
    public void TestAddGradeSuccess(){
        Assertions.assertEquals(1, service.saveNota("1", "1", 10, 1, "good"));
        service.deleteNota("1", "1");
    }

    @Test
    public void TestAddGradeInvalidStudentId(){
        Assertions.assertEquals(-1, service.saveNota("0", "1", 10, 1, "good"));
    }

    @Test
    public void TestAddGradeEmptyStudentId(){
        Assertions.assertEquals(-1, service.saveNota("", "1", 10, 1, "good"));
    }

    @Test
    public void TestAddGradeNullStudentId(){
        try {
            service.saveNota(null, "1", 10, 1, "good");
            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void TestAddGradeInvalidHomeworkId(){
        Assertions.assertEquals(-1, service.saveNota("1", "0", 10, 1, "good"));
    }

    @Test
    public void TestAddGradeEmptyHomeworkId(){
        Assertions.assertEquals(-1, service.saveNota("1", "", 10, 1, "good"));
    }

    @Test
    public void TestAddGradeNullHomeworkId(){
        try {
            service.saveNota("1", null, 10, 1, "good");
            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void TestAddGradeGradeHigherThan10(){
        Assertions.assertEquals(0, service.saveNota("1", "1", 11, 1, "good"));
    }

    @Test
    public void TestAddGradeGradeLowerThan0(){
        Assertions.assertEquals(0, service.saveNota("1", "1", -1, 1, "good"));
    }

    @Test
    public void TestAddGradeInvalidWeek(){
        Assertions.assertEquals(0, service.saveNota("1", "1", 10, -1, "good"));
    }
}
