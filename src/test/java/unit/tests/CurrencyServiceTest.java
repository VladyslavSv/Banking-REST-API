package unit.tests;


import com.practice.bank.Application;
import com.practice.bank.model.Currency;
import com.practice.bank.services.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;


    @Test
    public void testAddCurrency(){

        Currency currency=new Currency();
        currency.setRate(new BigDecimal(1.2));
        currency.setName("EUR");

        currency = currencyService.addCurrency(currency);

        assertNotNull(currency);
    }

    @Test
    public void testRemoveCurrencyById(){
        Long id = 3L;

        currencyService.removeCurrencyById(id);

        Currency currency=currencyService.getCurrencyByid(3L);

        assertNull( currency );
    }

    @Test
    public void testEditCurrency(){
        Long id = 2L;

        Currency currency = currencyService.getCurrencyByid( id );
        currency.setRate( new BigDecimal(22.8) );

        currency = currencyService.updateCurrency( currency );

        assertNotNull( currency );
    }

    @Test
    public void testGetCurrency(){
        Long id = 2L;

        Currency currency = currencyService.getCurrencyByid( id );

        assertNotNull( currency );
    }
}
