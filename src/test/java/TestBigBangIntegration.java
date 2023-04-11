import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;


public class TestBigBangIntegration {
    private Service service;
    private StudentXMLRepository studentXmlRepo;
    private TemaXMLRepository temaXmlRepo;
    private NotaXMLRepository notaXmlRepo;

    @BeforeEach
    public void setUp() {
        studentXmlRepo = new StudentXMLRepository(new StudentValidator(), "src/test/java/studenti.xml");
        temaXmlRepo = new TemaXMLRepository(new TemaValidator(), "src/test/java/teme.xml");
        notaXmlRepo = new NotaXMLRepository(new NotaValidator(), "src/test/java/note.xml");
        service = new Service(studentXmlRepo, temaXmlRepo, notaXmlRepo);
    }

    @Test
    public void TestAddStudentSuccess(){
        Assertions.assertEquals(1,service.saveStudent("50", "Mihailescu", 931));
        service.deleteStudent("50");
    }

    @Test
    public void TestAddAssignmentSuccess(){
        Assertions.assertEquals(1, service.saveTema("50", "description", 5, 1));
        service.deleteTema("50");
    }

    @Test
    public void TestAddGradeSuccess(){
        Assertions.assertEquals(1, service.saveNota("1", "1", 10, 1, "good"));
        service.deleteNota("1", "1");
    }

    @Test
    public void TestBigBangIntegration() {
        Assertions.assertEquals(1, service.saveStudent("100", "name", 931));
        Assertions.assertEquals(1, service.saveTema("100", "description", 1, 1));
        Assertions.assertEquals(1, service.saveNota("100", "100", 10, 1, "good"));
        Assertions.assertEquals(1, service.deleteStudent("100"));
        Assertions.assertEquals(1, service.deleteTema("100"));
        Assertions.assertEquals(1, service.deleteNota("100", "100"));
    }
}
