import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

public class TestAddStudent {

    private StudentValidator studentValidator = new StudentValidator();
    private NotaValidator notaValidator = new NotaValidator();
    private TemaValidator temaValidator = new TemaValidator();

    private StudentXMLRepository studentXMLRepo = new StudentXMLRepository(studentValidator, "./src/test/java/studenti.xml");
    private NotaXMLRepository notaXMLRepo = new NotaXMLRepository(notaValidator, "./src/test/java/note.xml");
    private TemaXMLRepository temaXMLRepo = new TemaXMLRepository(temaValidator, "./src/test/java/teme.xml");

    private Service service = new Service(studentXMLRepo, temaXMLRepo, notaXMLRepo);


    @Test
    public void TestAddStudentSuccess(){
        Assertions.assertEquals(1,service.saveStudent("50", "Mihailescu", 931));
        service.deleteStudent("50");
    }

    @Test
    public void TestAddStudentInvalidGroupNumberHigher(){
        Assertions.assertEquals(0, service.saveStudent("98", "Georgescu", 999));
    }

    @Test
    public void TestAddStudentInvalidGroupNumberLower(){
        Assertions.assertEquals(0, service.saveStudent("98", "Georgescu", 109));
    }

    @Test
    public void TestAddStudentIdNull(){
        Assertions.assertEquals(0, service.saveStudent(null, "Georgescu", 931));
    }

    @Test
    public void TestAddStudentIdEmpty(){
        Assertions.assertEquals(0, service.saveStudent("", "Georgescu", 931));
    }

    @Test
    public void TestAddStudentIdNotUnique(){
        service.saveStudent("id", "Georgescu", 931);
        Assertions.assertEquals(0, service.saveStudent("id", "Georgescu", 931));
        service.deleteStudent("id");
    }

    @Test
    public void TestAddStudentNameNull(){
        Assertions.assertEquals(0, service.saveStudent("id", null, 931));
    }

    @Test
    public void TestAddStudentNameEmpty(){
        Assertions.assertEquals(0, service.saveStudent("id", "", 931));
    }
}
