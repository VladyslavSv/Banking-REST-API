package unit.tests;


import com.practice.bank.Application;
import com.practice.bank.dao.FirstNameRepository;
import com.practice.bank.model.FirstName;
import com.practice.bank.services.FirstNameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNull;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FirstNameServiceTest {

    private static Long testingId = 1L;

    private FirstName firstName;

    private FirstNameService firstNameService;

    @Mock
    private FirstNameRepository firstNameRepository;

    @Before
    public void beforeTesting() {
        firstNameService = new FirstNameService();

        firstName = new FirstName();
        firstName.setName("John");

        Optional<FirstName> result = Optional.of(firstName);

        Mockito
                .when( firstNameRepository.findById( FirstNameServiceTest.testingId ) )
                .thenReturn(result);

        Mockito
                .when( firstNameRepository.findFirstNameByName( firstName.getName() ) )
                .thenReturn( firstName );

        Mockito
                .when( firstNameRepository.save(firstName) )
                .thenReturn( firstName );

        firstNameService.setFirstNameRepository( firstNameRepository );
    }

    @Test
    public void testAddFirstName() {
        firstName = firstNameService.addIfNotExists( firstName );

        assertNotNull( firstName );
    }

    @Test
    public void testGetFirstName(){
        FirstName firstName = firstNameService.get( FirstNameServiceTest.testingId );

        assertNotNull( firstName );
    }
}
