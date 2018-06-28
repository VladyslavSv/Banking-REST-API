package unit.tests;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.Application;
import com.practice.bank.dao.TransactionTypeRepository;
import com.practice.bank.model.TransactionType;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.TransactionTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransactionTypeServiceTest {

    private static Long testingId = 1L;

    private TransactionTypeService typeService;

    @Mock
    private TransactionTypeRepository repository;

    @Before
    public void beforeTesting() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            typeService = mapper.readValue(new File(Config.RESOURCE_PATH + "typeService.json"), TransactionTypeService.class);

            TransactionType type = mapper.readValue(new File(Config.RESOURCE_PATH + "transactionType.json"), TransactionType.class);

            Optional<TransactionType> result = Optional.of(type);

            Mockito
                    .when(repository.findById(TransactionTypeServiceTest.testingId))
                    .thenReturn(result);

            typeService.setTransactionTypeRepository(repository);
        } catch (JsonGenerationException jsonGenerationException) {
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Test
    public void testGetTransactionType(){
        TransactionType innerType = typeService.getTransactionTypeById( TransactionTypeServiceTest.testingId );

        assertNotNull( innerType );
    }
}
