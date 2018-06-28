package unit.tests;


import com.practice.bank.Application;
import com.practice.bank.dao.WalletRepository;
import com.practice.bank.model.Currency;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.WalletService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WalletServiceTest {

    private static Long testingId = 1L;

    private WalletService walletService;

    @Mock
    private WalletRepository repository;

    @Before
    public void beforeTesting() {
        walletService = new WalletService();

        Currency currency = new Currency();
        currency.setRate(new BigDecimal(1.0));
        currency.setId( -1L );
        currency.setName("USD");

        Wallet wallet = new Wallet();
        wallet.setCurrency(currency);
        wallet.setAmount(new BigDecimal(1400));
        wallet.setId( -1L );

        Optional<Wallet> result = Optional.of(wallet);

        Mockito
                .when(repository.findById( WalletServiceTest.testingId ))
                .thenReturn(result);

        walletService.setWalletRepository(repository);
    }

    @Test
    public void testGetWalletById(){
        Wallet innerWallet = walletService.getWalletById( WalletServiceTest.testingId );

        assertNotNull(innerWallet);
    }

}
