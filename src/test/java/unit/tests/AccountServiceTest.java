package unit.tests;

import com.practice.bank.Application;
import com.practice.bank.model.Account;
import com.practice.bank.model.FirstName;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.FirstNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AccountServiceTest {

    public static Long accountId = 1L;
    @Autowired
    private AccountService accountService;

    @Autowired
    private FirstNameService firstNameService;

    @Test
    public void testAddAccount(){

        FirstName firstName = new FirstName("Alex");

        firstNameService.addIfNotExists(firstName);

        Account account = accountService.addAccount(new Account(firstName,"Tester","tester@gmail.com","10101010","tester098","098tester222"));

        AccountServiceTest.accountId = account.getId();

        assertNotNull(account);

    }

    @Test
    public void testGetAccount() {

        Account account = accountService.getAccount(AccountServiceTest.accountId);

        if (account != null) {
            assertEquals("Id of user ", AccountServiceTest.accountId, account.getId());
        }
    }

    @Test
    public void testRemoveAccount() {

        Account account = accountService.getAccount(AccountServiceTest.accountId);

        if (account != null) {
           accountService.removeAccount(account.getId());
        }

        account = accountService.getAccount(AccountServiceTest.accountId);

        assertNull(account);
    }

    @Test
    public void testEditAccount() {
        Account account = accountService.getAccount(AccountServiceTest.accountId);

        String newLastName = "Tester05";
        if(account != null) {
            account.setLastName(newLastName);
            account = accountService.editAccount(account);
        }
        assertEquals("User last name now ", newLastName, account.getLastName());
    }
}
