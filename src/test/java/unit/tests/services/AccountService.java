package unit.tests.services;

import com.practice.bank.dao.AccountRepository;
import com.practice.bank.model.Account;
import com.practice.bank.model.FirstName;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.mockito.Mockito.when;

@Service
public class AccountService {

    @Mock
    private AccountRepository accountRepository;

    public AccountService(){}

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public void removeAccount(Long id) {
            accountRepository.deleteById(id);
    }

    public Account editAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public Account getAccount(Long id) {

        Account account = new Account(new FirstName( "John" ),"Doe",
                "johndoe@gmail.com", "1010101010", "JohnDoe076", "p098John" );

        Optional<Account> result = Optional.of(account);

        when(accountRepository.findById(1L)).thenReturn(result);

        return result.isPresent()?result.get():null;
    }

    public Long validate(String login, String password) {
        Account account = accountRepository.getAccountByLoginAndPassword(login,password);

        if(account != null) {
            return account.getId();
        } else {
            return null;
        }
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
