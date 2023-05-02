import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ssvv.project.domain.Nota;
import ssvv.project.domain.Pair;
import ssvv.project.domain.Student;
import ssvv.project.domain.Tema;
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
        Student s = new Student("1", "name", 931);
        Tema t = new Tema("1", "desc", 10, 8);
        Mockito.when(studentXMLRepo.save(s)).thenReturn(null);
        Mockito.when(temaXMLRepo.save(t)).thenReturn(null);

        int sres = service.saveStudent("1", "name", 931);
        int tres = service.saveTema("1", "desc", 10, 8);

        assertEquals(1, sres);
        assertEquals(1, tres);
    }

    @Test
    public void saveGrade(){
        Student s = new Student("1", "name", 931);
        Tema t = new Tema("1", "desc", 10, 8);
        Nota n = new Nota(new Pair<String, String>("1", "1"), 10, 9, "good");
        Mockito.when(studentXMLRepo.save(s)).thenReturn(s);
        Mockito.when(temaXMLRepo.save(t)).thenReturn(t);
        Mockito.when(notaXMLRepo.save(n)).thenReturn(null);
        

        int sres = service.saveStudent("1", "name", 931);
        int tres = service.saveTema("1", "desc", 10, 8);
        int nres = service.saveNota("1", "1", 10, 9, "good");

        assertEquals(0, sres);
        assertEquals(0, tres);
        assertEquals(-1, nres);
    }
}
