package unit.tests;


import com.practice.bank.Application;
import com.practice.bank.dao.TransactionTypeRepository;
import com.practice.bank.model.TransactionType;
import com.practice.bank.services.TransactionTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        typeService = new TransactionTypeService();

        TransactionType type = new TransactionType();
        type.setName("Relief");
        type.setId( -1L );

        Optional<TransactionType> result = Optional.of(type);

        Mockito
                .when(repository.findById( TransactionTypeServiceTest.testingId ))
                .thenReturn(result);

        typeService.setTransactionTypeRepository(repository);
    }

    @Test
    public void testGetTransactionType(){
        TransactionType innerType = typeService.getTransactionTypeById( TransactionTypeServiceTest.testingId );

        assertNotNull( innerType );
    }
}
