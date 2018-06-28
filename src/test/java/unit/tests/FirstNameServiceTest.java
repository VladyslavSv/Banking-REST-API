package unit.tests;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.Application;
import com.practice.bank.dao.FirstNameRepository;
import com.practice.bank.model.FirstName;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.FirstNameService;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOuterJoinEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
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

        ObjectMapper mapper = new ObjectMapper();

        try {
            firstNameService = mapper.readValue(new File(Config.RESOURCE_PATH + "firstNameService.json"), FirstNameService.class);

            firstName = mapper.readValue(new File(Config.RESOURCE_PATH + "firstName.json"), FirstName.class);

            Optional<FirstName> result = Optional.of(firstName);

            Mockito
                    .when(firstNameRepository.findById(FirstNameServiceTest.testingId))
                    .thenReturn(result);

            Mockito
                    .when(firstNameRepository.save(firstName))
                    .thenReturn(firstName);

            firstNameService.setFirstNameRepository(firstNameRepository);
        } catch (JsonGenerationException jsonGenerationException) {
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
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
