import org.junit.Test;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

import static junit.framework.TestCase.assertEquals;

public class testAdd {

    private StudentValidator studentValidator = new StudentValidator();
    private NotaValidator notaValidator = new NotaValidator();
    private TemaValidator temaValidator = new TemaValidator();

    private StudentXMLRepository studentXMLRepo = new StudentXMLRepository(studentValidator, "studenti.xml");
    private NotaXMLRepository notaXMLRepo = new NotaXMLRepository(notaValidator, "teme.xml");
    private TemaXMLRepository temaXMLRepo = new TemaXMLRepository(temaValidator, "note.xml");

    private Service service = new Service(studentXMLRepo, temaXMLRepo, notaXMLRepo);


    @Test
    public void testAddStudentSuccess(){
        assertEquals(1,service.saveStudent("50", "Mihailescu", 931));
        service.deleteStudent("50");
    }

    @Test
    public void testAddStudentFailure(){
        assertEquals(0, service.saveStudent("98", "Georgescu", 999));
    }
}
