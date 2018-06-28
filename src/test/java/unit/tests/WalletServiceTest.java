package unit.tests;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.Application;
import com.practice.bank.dao.WalletRepository;
import com.practice.bank.model.Currency;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AccountService;
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
import java.io.File;
import java.io.IOException;
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
        ObjectMapper mapper = new ObjectMapper();

        try {
            walletService = mapper.readValue(new File(Config.RESOURCE_PATH + "walletService.json"), WalletService.class);

            Wallet wallet = mapper.readValue(new File(Config.RESOURCE_PATH + "wallet.json"), Wallet.class);

            Optional<Wallet> result = Optional.of(wallet);

            Mockito
                    .when(repository.findById(WalletServiceTest.testingId))
                    .thenReturn(result);

            walletService.setWalletRepository(repository);
        } catch (JsonGenerationException jsonGenerationException) {
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Test
    public void testGetWalletById(){
        Wallet innerWallet = walletService.getWalletById( WalletServiceTest.testingId );

        assertNotNull(innerWallet);
    }

}
