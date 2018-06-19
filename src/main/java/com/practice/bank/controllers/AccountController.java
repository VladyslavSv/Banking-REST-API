package com.practice.bank.controllers;

import com.practice.bank.services.TransactionTypeService;
import com.practice.bank.services.Validators.EmailValidator;
import com.practice.bank.services.Validators.PasswordValidator;
import com.practice.bank.services.Validators.UsernameValidator;
import com.practice.bank.model.*;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.FirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    @Autowired
    private FirstNameService firstNameService;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @PutMapping(value="/add")
    public Account add(@RequestParam(value="account")Account account)
    {
        if(validateFields(account))
        {
            FirstName fn=new FirstName(account.getFirstName().getName());
            firstNameService.addIfNotExists(fn);

            accountService.addAccount(account);

            return account;
        }
        else
        {
           return null;
        }

    }
    @PostMapping(value="/update")
    public Account update(@RequestParam(value="account") Account account)
    {
        if(account!=null&&validateFields(account))
        {
            FirstName fn=new FirstName(account.getFirstName().getName());
            firstNameService.addIfNotExists(fn);

            accountService.editAccount(account);
            return account;
        }
        else
        {
            return null;
        }
    }
    @DeleteMapping(value="/remove")
    public void remove(@RequestParam(value="id")Long id) {
        accountService.removeAccount(id);
    }

    @GetMapping(value="/get")
    public Account get(@RequestParam(value = "id")Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping(value="/getFullName")
    public String getName(@RequestParam(value="id")Long id) {
        Account account=accountService.getAccount(id);
        return account.getFullName();
    }
    @GetMapping(value="/getFirstName")
    public String getFirstName(@RequestParam(value="id")Long id) {
        Account account=accountService.getAccount(id);
        return account.getFirstName().getName();
    }
    @GetMapping(value="/getLastName")
    public String getLastName(@RequestParam(value="id")Long id) {
        Account account=accountService.getAccount(id);
        return account.getLastName();
    }
    @GetMapping(value="/getEmail")
    public String getEmail(@RequestParam(value="id")Long id) {
        Account account=accountService.getAccount(id);
        return account.getEmail();
    }
    @GetMapping(value="/getCode")
    public String getCode(@RequestParam(value="id") Long id) {
        Account account=accountService.getAccount(id);
        return account.getCode();
    }
    @GetMapping(value="/validate")
    public Long validateUser(@RequestParam(value="login")String login,
                             @RequestParam(value="password") String password) {
        Long id=accountService.validate(login, password);
        if(id!=null)
        {
            return id;
        }
        else
        {
             return -1L;
        }
    }
    private boolean validateFields(Account account) {
        try {
            if (!account.getFirstName().getName().chars().allMatch(Character::isLetter) || !account.getLastName().chars().allMatch(Character::isLetter))
            {
                throw new Exception();
            }

            Integer.parseInt(account.getCode());

            if(!UsernameValidator.validate(account.getLogin())||
                    !EmailValidator.validateEmail(account.getEmail())||
                    !PasswordValidator.validate(account.getPassword()))
            {
                throw new Exception();
            }
        }
        catch (Exception ex)
        {
            return false;
        }

        return true;
    }
}
