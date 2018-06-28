package unit.tests;

import com.practice.bank.Application;
import com.practice.bank.dao.WalletTransactionRepository;
import com.practice.bank.model.Currency;
import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
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
    private WalletService walletService;

    @Mock
    private WalletTransactionRepository repository;

    private WalletTransactionService walletTransactionService;

    @Before
    public void beforeTesting() {
        walletTransactionService = new WalletTransactionService();

        Currency currency = new Currency();
        currency.setRate( new BigDecimal(1.0) );
        currency.setId( -1L );
        currency.setName( "USD" );

        Wallet testedWallet = new Wallet();
        testedWallet.setCurrency( currency );
        testedWallet.setAmount( new BigDecimal(1400) );
        testedWallet.setId( WalletTransactionServiceTest.testingId );

        Wallet newTestedWallet = new Wallet();
        newTestedWallet.setCurrency( currency );
        newTestedWallet.setAmount( new BigDecimal(1500) );
        newTestedWallet.setId( WalletTransactionServiceTest.anotherTestingId );

        transaction =  new WalletTransaction();
        transaction.setDate( new Date() );
        transaction.setId( -1L );
        transaction.setSumSent( new BigDecimal(100) );
        transaction.setSender(testedWallet);
        transaction.setReceiver(newTestedWallet);

        Optional<WalletTransaction> result = Optional.of(transaction);
        Mockito
                .when(walletService.getWalletById( WalletTransactionServiceTest.testingId ))
                .thenReturn(testedWallet);

        Mockito
                .when(walletService.getWalletById( WalletTransactionServiceTest.anotherTestingId ))
                .thenReturn(newTestedWallet);

        Mockito
                .when(repository.save(transaction))
                .thenReturn(transaction);

        Mockito
                .when(repository.findById(WalletTransactionServiceTest.testingId))
                .thenReturn(result);

        walletTransactionService.setWalletTransactionRepository(repository);
    }

    @Test
    public void testGetWalletTransaction() {
        transaction = walletTransactionService.getWalletTransaction( WalletTransactionServiceTest.testingId );

        assertNotNull(transaction);
    }

    @Test
    public void testCommitTransaction() {

        Wallet wallet = walletService.getWalletById( WalletTransactionServiceTest.testingId );
        Wallet newWallet = walletService.getWalletById( WalletTransactionServiceTest.anotherTestingId );

        transaction = walletTransactionService.commitWalletTransaction(transaction);

        assertEquals(1300,wallet.getAmount().intValue());
        assertEquals(1600,newWallet.getAmount().intValue());
    }
}
