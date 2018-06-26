package unit.tests;

import com.practice.bank.Application;
import com.practice.bank.model.AtmTransaction;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AtmTransactionService;
import com.practice.bank.services.TransactionTypeService;
import com.practice.bank.services.WalletService;
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
public class AtmTransactionServiceTest {

    @Autowired
    private AtmTransactionService atmService;
    @Autowired
    private TransactionTypeService tService;
    @Autowired
    private WalletService walletService;

    @Test
    public void testCommit(){
        AtmTransaction transaction = new AtmTransaction();
        transaction.setDate( new Date() );
        transaction.setSum( new BigDecimal( 500 ) );
        transaction.setTransactionType(tService.getTransactionTypeById( 1L ) );
        transaction.setWallet(walletService.getWalletById( 2L ) );

        transaction = atmService.commitTransaction(transaction);

        assertNotNull(transaction);
    }

    @Test
    public void testGetAtmTransactionById(){
        AtmTransaction transaction = atmService.getAtmTransactionById( 1L );

        assertNotNull( transaction );
    }

    @Test
    public void testGetTransactionsByWallet(){
        Wallet wallet = walletService.getWalletById(2L);

        List<AtmTransaction> atmTransactions = atmService.getTransactionsByWallet(wallet);

        assertNotNull(atmTransactions);
    }

}

