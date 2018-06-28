package unit.tests;

import com.practice.bank.Application;
import com.practice.bank.model.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import unit.tests.services.CurrencyService;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CurrencyServiceTest {

    private CurrencyService currencyService;

    @Test
    public void testAddCurrency() {
        Currency currency = new Currency();
        currency.setRate( new BigDecimal(1.2) );
        currency.setName("EUR");

        currency = currencyService.addCurrency( currency );

        assertNotNull(currency);
    }

    @Test
    public void testRemoveCurrencyById() {
        //currencyService.removeCurrencyById( id );
    }

    @Test
    public void testEditCurrency() {
        Long id = 2L;

        Currency currency = currencyService.getCurrencyByid( id );
        currency.setRate( new BigDecimal(22.8) );

        currency = currencyService.updateCurrency( currency );

        assertNotNull( currency );
    }

    @Test
    public void testGetCurrency() {
        Long id = 1L;

        Currency currency = currencyService.getCurrencyByid( id );

        assertNotNull( currency );
    }
}
