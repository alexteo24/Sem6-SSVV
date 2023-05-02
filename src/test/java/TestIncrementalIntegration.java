import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ssvv.project.domain.Student;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;
import ssvv.project.validation.ValidationException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestIncrementalIntegration {
    @Mock
    private StudentValidator studentValidator;
    @Mock
    private NotaValidator notaValidator;
    @Mock
    private TemaValidator temaValidator;

    @Mock
    private StudentXMLRepository studentXMLRepo;
    @Mock
    private NotaXMLRepository notaXMLRepo;
    @Mock
    private TemaXMLRepository temaXMLRepo;

    @Mock
    private Service service;

    @Test
    public void testValidate() {
        studentValidator = Mockito.mock(StudentValidator.class);
//        try {
//            var student = new Student("1", "name", 936);
//            studentValidator.validate(student);
//        } catch (ValidationException e) {
//            assert(false);
//        }
        try {
            var student = new Student("", "name", 9136);
            assert(false);
        } catch (ValidationException e) {
            assert(true);
        }

    }
}
