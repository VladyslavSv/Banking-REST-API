package unit.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.model.AtmTransaction;
import com.practice.bank.model.Currency;
import com.practice.bank.model.TransactionType;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AtmTransactionService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class Test {
    public static void main(String ... args) {

        ObjectMapper mapper = new ObjectMapper();

        AtmTransactionService service = new AtmTransactionService();

        try {
            mapper.writeValue(new File("src/main/resources/JsonObjects/atmService.json"), service);
        } catch (JsonGenerationException jsonGenerationException){
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
