package unit.tests;


import com.practice.bank.Application;
import com.practice.bank.model.FirstName;
import com.practice.bank.services.FirstNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FirstNameServiceTest {

    @Autowired
    private FirstNameService firstNameService;


    @Test
    public void testAddFirstName() {
        FirstName firstName = new FirstName( "Jason" );

        firstName = firstNameService.addIfNotExists( firstName );

        assertNotNull( firstName );
    }

    @Test
    public void testRemoveFirstName(){
        Long id = 3L;

        firstNameService.remove( id );

        FirstName firstName = firstNameService.get( id );

        assertNull( firstName );
    }

    @Test
    public void testChangeFirstName(){
        Long id = 1L;

        FirstName firstName = firstNameService.get( id );
        firstName.setName( "Anatoliy" );

        firstName = firstNameService.change( firstName );

        assertNotNull( firstName );
    }

    @Test
    public void testGetFirstName(){
        Long id = 1L;

        FirstName firstName = firstNameService.get( id );

        assertNotNull( firstName );
    }
}
