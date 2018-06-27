package unit.tests;


import com.practice.bank.Application;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.WalletService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    public void testAddWallet(){
        Wallet wallet = new Wallet();
        wallet.setAmount( new BigDecimal( 1000 ) );

        walletService.addWallet( wallet );

        assertNotNull( wallet );
    }

    @Test
    public void testRemoveWallet(){
        Wallet wallet = walletService.getWalletById( 1L );

        walletService.removeWalletById( wallet.getId() );

        wallet = walletService.getWalletById( 1L );

        assertNull( wallet );
    }

    @Test
    public void testGetWalletById(){
        Wallet wallet = walletService.getWalletById( 2L );

        assertNotNull(wallet);
    }

    @Test
    public void testGetWalletsByAccount_Id(){
        List<Wallet> wallets = walletService.getWalletsByAccount_Id( 1L );

        assertNotNull( wallets );
    }
}
