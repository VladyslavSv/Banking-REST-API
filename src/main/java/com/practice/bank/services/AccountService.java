package com.practice.bank.services;


import com.practice.bank.dao.AccountRepository;
import com.practice.bank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService
{
    @Autowired
    AccountRepository accountRepository;

    public AccountService(){}

    public Account addAccount(Account account)
    {
        return accountRepository.save(account);
    }
    public void removeAccount(Long id)
    {
        accountRepository.deleteById(id);
    }
    public void editAccount(Account account)
    {
        accountRepository.save(account);
    }
    public Account getAccount(Long id)
    {
        return accountRepository.findById(id).get();
    }
    public Long validate(String login,String password)
    {
        Account account=accountRepository.getAccountByLoginAndPassword(login,password);
        if(account!=null)
        {
            return account.getId();
        }
        else
        {
            return null;
        }
    }
}
