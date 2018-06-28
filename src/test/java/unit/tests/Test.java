package unit.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.services.AccountService;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String ... args) {

        ObjectMapper mapper = new ObjectMapper();

        AccountService service = new AccountService();

        try {
            mapper.writeValue(new File("src/main/resources/JsonObjects/accountService.json"), service);
        } catch (JsonGenerationException jsonGenerationException){
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
