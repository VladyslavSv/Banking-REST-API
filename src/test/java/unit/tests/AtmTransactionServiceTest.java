package unit.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.Application;
import com.practice.bank.dao.ATMTransactionRepository;
import com.practice.bank.model.AtmTransaction;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AtmTransactionService;
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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AtmTransactionServiceTest {

    private Long testingId = 1L;

    private AtmTransaction transaction;

    private AtmTransactionService atmService;

    @Mock
    private ATMTransactionRepository atmTransactionRepository;

    @Before
    public void beforeTesting() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            atmService = mapper.readValue(new File(Config.RESOURCE_PATH + "atmService.json"), AtmTransactionService.class);

            transaction = mapper.readValue(new File(Config.RESOURCE_PATH + "atmTransaction.json"), AtmTransaction.class);

            Optional<AtmTransaction> result = Optional.of(transaction);

            Mockito
                    .when(atmTransactionRepository.findById(testingId))
                    .thenReturn(result);

            Mockito
                    .when(atmTransactionRepository.save(transaction))
                    .thenReturn(transaction);

            atmService.setAtmTransactionRepository(atmTransactionRepository);

        } catch (JsonGenerationException jsonGenerationException) {
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    @Test
    public void testCommit() {
        Wallet wallet = transaction.getWallet();

        int testingBalance = 550;

        transaction = atmService.commitTransaction(transaction);

        assertEquals( testingBalance, wallet.getAmount().intValue() );
    }

    @Test
    public void testGetAtmTransactionById() {
        AtmTransaction transaction = atmService.getAtmTransactionById( testingId );

        assertNotNull( transaction );
    }
}

