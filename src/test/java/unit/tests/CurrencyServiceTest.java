package unit.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.File;
import java.io.IOException;
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

        ObjectMapper mapper = new ObjectMapper();

        try {
            currencyService = mapper.readValue(
                    new File(Config.RESOURCE_PATH + "currencyService.json"), CurrencyService.class);

            Currency currency = mapper.readValue(
                    new File(Config.RESOURCE_PATH + "currency.json"), Currency.class);

            Optional<Currency> result = Optional.of(currency);

            Mockito
                    .when(repository.findById(CurrencyServiceTest.testingId))
                    .thenReturn(result);

            currencyService.setCurrencyRepository(repository);
        } catch (JsonGenerationException jsonGenerationException) {
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Test
    public void testGetCurrency() {
        Currency currency = currencyService.getCurrencyByid( CurrencyServiceTest.testingId );

        assertNotNull( currency );
    }
}
