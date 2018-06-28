package unit.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.Application;
import com.practice.bank.dao.WalletTransactionRepository;
import com.practice.bank.model.Currency;
import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.WalletService;
import com.practice.bank.services.WalletTransactionService;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WalletTransactionServiceTest {

    private static Long testingId = 1L;

    private static Long anotherTestingId = 2L;

    private WalletTransaction transaction;

    @Mock
    private WalletTransactionRepository repository;

    private WalletTransactionService walletTransactionService;

    @Before
    public void beforeTesting() {

        ObjectMapper mapper = new ObjectMapper();

        try {

            walletTransactionService = mapper.readValue(new File(Config.RESOURCE_PATH + "walletTransactionService.json"), WalletTransactionService.class);

            transaction = mapper.readValue(new File(Config.RESOURCE_PATH + "walletTransaction.json"), WalletTransaction.class);

            Optional<WalletTransaction> result = Optional.of(transaction);

            Mockito
                    .when(repository.save(transaction))
                    .thenReturn(transaction);

            Mockito
                    .when(repository.findById(WalletTransactionServiceTest.testingId))
                    .thenReturn(result);

            walletTransactionService.setWalletTransactionRepository(repository);

        } catch (JsonGenerationException jsonGenerationException) {
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Test
    public void testGetWalletTransaction() {
        transaction = walletTransactionService.getWalletTransaction( WalletTransactionServiceTest.testingId );

        assertNotNull(transaction);
    }

    @Test
    public void testCommitTransaction() {
        
        transaction = walletTransactionService.commitWalletTransaction(transaction);

        Wallet sender = transaction.getSender();
        Wallet receiver = transaction.getReceiver();

        int senderBalanceAfterSending = 400;
        int receiverBalanceAfterReceiving = 3500;

        assertEquals( senderBalanceAfterSending, sender.getAmount().intValue() );
        assertEquals( receiverBalanceAfterReceiving, receiver.getAmount().intValue() );
    }
}
