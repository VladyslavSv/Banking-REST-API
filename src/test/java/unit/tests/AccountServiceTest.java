package unit.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bank.Application;
import com.practice.bank.dao.AccountRepository;
import com.practice.bank.model.Account;
import com.practice.bank.model.FirstName;
import com.practice.bank.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AccountServiceTest {

    private static long accountId = 1L;

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @Before
    public void beforeTesting() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            accountService = mapper.readValue(new File(Config.RESOURCE_PATH + "accountService.json"), AccountService.class);

            Account account = mapper.readValue(new File(Config.RESOURCE_PATH + "account.json"), Account.class);

            Optional<Account> result = Optional.of(account);

            when(accountRepository.findById(accountId)).thenReturn(result);

            accountService.setAccountRepository(accountRepository);
        } catch (JsonGenerationException jsonGenerationException) {
            jsonGenerationException.printStackTrace();
        } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    @Test
    public void testGetAccount() {
        Account account = accountService.getAccount(AccountServiceTest.accountId);

        assertNotNull(account);
    }
}
