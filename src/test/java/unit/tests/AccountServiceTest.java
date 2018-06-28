package unit.tests;

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
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
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
        accountService = new AccountService();

        Account account = new Account(new FirstName( "John" ),"Doe",
                "johndoe@gmail.com", "1010101010", "JohnDoe076", "p098John" );

        Optional<Account> result = Optional.of(account);

        when(accountRepository.findById(accountId)).thenReturn(result);

        accountService.setAccountRepository(accountRepository);
    }
    @Test
    public void testGetAccount() {
        Account account = accountService.getAccount(AccountServiceTest.accountId);

        assertNotNull(account);
    }
}
