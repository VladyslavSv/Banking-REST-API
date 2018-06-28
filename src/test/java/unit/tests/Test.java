package unit.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.services.FirstNameService;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String ... args) {

        ObjectMapper mapper = new ObjectMapper();

        FirstNameService firstNameService = new FirstNameService();

        try {
            mapper.writeValue(new File("src/main/resources/JsonObjects/firstNameService.json"), firstNameService);
        } catch (JsonGenerationException jsonGenerationException){
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
