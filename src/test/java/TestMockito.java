import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ssvv.project.domain.Student;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestMockito {

    @Mock
    StudentXMLRepository studentXMLRepo;
    @Mock
    NotaXMLRepository notaXMLRepo;
    @Mock
    TemaXMLRepository temaXMLRepo;

    StudentValidator sval;
    TemaValidator tval;
    NotaValidator nval;

    Service service;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        sval = new StudentValidator();
        tval = new TemaValidator();
        nval = new NotaValidator();
        service = new Service(studentXMLRepo, temaXMLRepo, notaXMLRepo);
    }

    @Test
    public void saveStudent(){
        Student s = new Student("1", "name", 931);
        Mockito.when(studentXMLRepo.save(s)).thenReturn(null);

        int res = service.saveStudent("1", "name", 931);


        assertEquals(1, res);
    }

    @Test
    public void saveAssignment(){

    }
}
