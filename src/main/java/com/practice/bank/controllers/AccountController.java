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

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/accounts")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    @Autowired
    private FirstNameService firstNameService;

    @GetMapping(value="/test")
    public TransactionType test()
    {
        return null;
    }

    @GetMapping(value="/add")
    public Account add(@RequestParam(value="firstName")String firstName,
                           @RequestParam(value="lastName")String lastName,
                           @RequestParam(value="email")String email,
                           @RequestParam(value="code")String code,
                           @RequestParam(value="login")String login,
                           @RequestParam(value="password")String password)
    {
        if(validateFields(firstName,lastName,email,code,login,password))
        {
            FirstName fn=new FirstName(firstName);
            firstNameService.addIfNotExists(fn);

            Account account=new Account(fn,lastName,email,code,login,password);
            accountService.addAccount(account);
            return account;
        }
        else
        {
           return null;
        }

    }
    @GetMapping(value="/update")
    public Account update(@RequestParam(value="id") Long id,
                              @RequestParam(value="firstName")String firstName,
                              @RequestParam(value="lastName")String lastName,
                              @RequestParam(value="email")String email,
                              @RequestParam(value="code")String code,
                              @RequestParam(value="login")String login,
                              @RequestParam(value="password")String password)
    {
        Account account= accountService.getAccount(id);
        if(account!=null&&validateFields(firstName,lastName,email,code,login,password))
        {
            FirstName fn=new FirstName(firstName);
            firstNameService.addIfNotExists(fn);

            account.setFirstName(fn);
            account.setLastName(lastName);
            account.setCode(code);
            account.setEmail(email);
            account.setLogin(login);
            account.setPassword(password);

            accountService.editAccount(account);

            return account;
        }
        else
        {
            return null;
        }
    }
    @GetMapping(value="/remove")
    public void remove(@RequestParam(value="id")Long id)
    {
        accountService.removeAccount(id);
    }

    @GetMapping(value="/get")
    public Account get(@RequestParam(value = "id")Long id)
    {
        return accountService.getAccount(id);
    }

    @GetMapping(value="/getFullName")
    public String getName(@RequestParam(value="id")Long id)
    {
        Account account=accountService.getAccount(id);
        return account.getFullName();
    }
    @GetMapping(value="/getFirstName")
    public String getFirstName(@RequestParam(value="id")Long id)
    {
        Account account=accountService.getAccount(id);
        return account.getFirstName().getName();
    }
    @GetMapping(value="/getLastName")
    public String getLastName(@RequestParam(value="id")Long id)
    {
        Account account=accountService.getAccount(id);
        return account.getLastName();
    }
    @GetMapping(value="/getEmail")
    public String getEmail(@RequestParam(value="id")Long id)
    {
        Account account=accountService.getAccount(id);
        return account.getEmail();
    }
    @GetMapping(value="/getCode")
    public String getCode(@RequestParam(value="id") Long id)
    {
        Account account=accountService.getAccount(id);
        return account.getCode();
    }
    @GetMapping(value="/validate")
    public Long validateUser(@RequestParam(value="login")String login,
                             @RequestParam(value="password") String password)
    {
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
    private boolean validateFields(String firstName,String lastName,String email,String code,String login,String password) {
        try {
            if (!firstName.chars().allMatch(Character::isLetter) || !lastName.chars().allMatch(Character::isLetter))
            {
                throw new Exception();
            }

            Integer.parseInt(code);

            if(!UsernameValidator.validate(login)||
                    !EmailValidator.validateEmail(email)||
                    !PasswordValidator.validate(password))
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
