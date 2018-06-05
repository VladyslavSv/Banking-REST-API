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
    public void editById(Account account)
    {
        accountRepository.save(account);
    }
    public Account getAccount(Long id)
    {
        return accountRepository.findById(id).get();
    }
}
