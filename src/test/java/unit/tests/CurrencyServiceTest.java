package unit.tests;

import com.practice.bank.Application;
import com.practice.bank.dao.CurrencyRepository;
import com.practice.bank.model.Currency;
import com.practice.bank.services.CurrencyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CurrencyServiceTest {

    private static Long testingId = 1L;

    private CurrencyService currencyService;

    @Mock
    private CurrencyRepository repository;

    @Before
    public void beforeTesting() {
        currencyService = new CurrencyService();

        Currency currency = new Currency();
        currency.setName("USD");
        currency.setRate(new BigDecimal(1.0));
        currency.setId( -1L );

        Optional<Currency> result = Optional.of(currency);

        Mockito
                .when(repository.findById(CurrencyServiceTest.testingId))
                .thenReturn(result);

        currencyService.setCurrencyRepository(repository);
    }

    @Test
    public void testGetCurrency() {
        Currency currency = currencyService.getCurrencyByid( CurrencyServiceTest.testingId );

        assertNotNull( currency );
    }
}
