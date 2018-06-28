package unit.tests;

import com.practice.bank.Application;
import com.practice.bank.dao.ATMTransactionRepository;
import com.practice.bank.dao.TransactionTypeRepository;
import com.practice.bank.model.AtmTransaction;
import com.practice.bank.model.Currency;
import com.practice.bank.model.TransactionType;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AtmTransactionService;
import com.practice.bank.services.TransactionTypeService;
import com.practice.bank.services.WalletService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AtmTransactionServiceTest {

    private static Long testingId = 1L;

    private AtmTransaction transaction;

    private AtmTransactionService atmService;

    @Mock
    private TransactionTypeService tService;

    @Mock
    private WalletService walletService;

    @Mock
    private ATMTransactionRepository atmTransactionRepository;

    @Before
    public void beforeTesting() {
        atmService = new AtmTransactionService();

        Currency currency = new Currency();
        currency.setRate( new BigDecimal(1.0) );
        currency.setName( "USD" );
        currency.setId( -1L );

        Wallet wallet = new Wallet();
        wallet.setAmount( new BigDecimal(500) );
        wallet.setCurrency( currency );
        wallet.setId( -1L );

        TransactionType type = new TransactionType();
        type.setName( "Relief" );
        type.setId( -1L );

        transaction = new AtmTransaction();
        transaction.setSum( new BigDecimal(50) );
        transaction.setDate( new Date() );
        transaction.setWallet( wallet );
        transaction.setTransactionType( type );
        transaction.setId( -1L );

        Optional<AtmTransaction> result = Optional.of( transaction );

        Mockito
                .when( walletService.getWalletById( AtmTransactionServiceTest.testingId ) )
                .thenReturn(wallet);

        Mockito
                .when( tService.getTransactionTypeById( AtmTransactionServiceTest.testingId ) )
                .thenReturn(type);

        Mockito
                .when(atmTransactionRepository.findById( AtmTransactionServiceTest.testingId ))
                .thenReturn(result);

        Mockito
                .when(atmTransactionRepository.save(transaction))
                .thenReturn(transaction);

        atmService.setAtmTransactionRepository(atmTransactionRepository);
    }
    @Test
    public void testCommit() {

        Wallet wallet = walletService.getWalletById( AtmTransactionServiceTest.testingId );
        TransactionType type = tService.getTransactionTypeById( AtmTransactionServiceTest.testingId );

        transaction = atmService.commitTransaction(transaction);

        assertEquals(550,wallet.getAmount().intValue());
        assertEquals(50,transaction.getSum().intValue());
    }

    @Test
    public void testGetAtmTransactionById() {
        AtmTransaction transaction = atmService.getAtmTransactionById( AtmTransactionServiceTest.testingId );

        assertNotNull( transaction );
    }
}

