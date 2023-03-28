import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

public class TestAddAssignment {
    private StudentValidator studentValidator = new StudentValidator();
    private NotaValidator notaValidator = new NotaValidator();
    private TemaValidator temaValidator = new TemaValidator();

    private StudentXMLRepository studentXMLRepo = new StudentXMLRepository(studentValidator, "./src/test/java/studenti.xml");
    private NotaXMLRepository notaXMLRepo = new NotaXMLRepository(notaValidator, "./src/test/java/note.xml");
    private TemaXMLRepository temaXMLRepo = new TemaXMLRepository(temaValidator, "./src/test/java/teme.xml");

    private Service service = new Service(studentXMLRepo, temaXMLRepo, notaXMLRepo);

    @Test
    public void TestAddAssignmentSuccess(){
        Assertions.assertEquals(1, service.saveTema("50", "description", 5, 1));
        service.deleteTema("50");
    }

    @Test
    public void TestAddAssignmentDeadlineLower(){
        Assertions.assertEquals(0, service.saveTema("50", "description", 0, 1));
    }

    @Test
    public void TestAddAssignmentDeadlineHigher(){
        Assertions.assertEquals(0, service.saveTema("50", "description", 15, 1));
    }

    @Test
    public void TestAddAssignmentStartlineLower(){
        Assertions.assertEquals(0, service.saveTema("50", "description", 5, 0));
    }

    @Test
    public void TestAddAssignmentStartlineHigherThanDeadline(){
        Assertions.assertEquals(0, service.saveTema("50", "description", 5, 15));
    }
    @Test
    public void TestAddAssignmentStartlineHigher(){
        Assertions.assertEquals(0, service.saveTema("50", "description", 5, 15));
    }

    @Test
    public void TestAddAssignmentIdNull(){
        Assertions.assertEquals(0, service.saveTema(null, "description", 5, 1));
    }

    @Test
    public void TestAddAssignmentIdEmpty(){
        Assertions.assertEquals(0, service.saveTema("", "description", 5, 1));
    }

    @Test
    public void TestAddAssignmentIdNotUnique(){
        service.saveTema("id", "description", 5, 1);
        Assertions.assertEquals(0, service.saveTema("id", "description", 5, 1));
        service.deleteTema("id");
    }

    @Test
    public void TestAddAssignmentDescriptionNull(){
        Assertions.assertEquals(0, service.saveTema("id", null, 5, 1));
    }

    @Test
    public void TestAddAssignmentDescriptionEmpty(){
        Assertions.assertEquals(0, service.saveTema("id", "", 5, 1));
    }
}
