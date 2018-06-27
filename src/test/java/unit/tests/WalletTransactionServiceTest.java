package unit.tests;

import com.practice.bank.Application;
import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
import com.practice.bank.services.WalletService;
import com.practice.bank.services.WalletTransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WalletTransactionServiceTest {

    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private WalletService walletService;

    @Test
    public void testGetWalletTransaction() {
        WalletTransaction walletTransaction = walletTransactionService.getWalletTransaction(1L);

        assertNotNull(walletTransaction);
    }

    @Test
    public void testGetWalletTransactions() {
        Wallet wallet = walletService.getWalletById(2L);

        List<WalletTransaction> walletTransactions = walletTransactionService.getWalletTransactions(wallet);

        assertNotNull(walletTransactions);
    }

    @Test
    public void testCommitTransaction() {

        Wallet wallet = walletService.getWalletById(1L);
        Wallet newWallet = walletService.getWalletById(2L);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setReceiver(wallet);
        transaction.setSender(newWallet);
        transaction.setDate(new Date());
        transaction.setSumSent(new BigDecimal(100));

        transaction = walletTransactionService.commitWalletTransaction(transaction);

        assertNotNull(transaction);
    }
}
