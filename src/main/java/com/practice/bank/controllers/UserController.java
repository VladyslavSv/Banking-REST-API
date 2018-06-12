package com.practice.bank.controllers;

import com.practice.bank.services.Validators.EmailValidator;
import com.practice.bank.services.Validators.PasswordValidator;
import com.practice.bank.services.Validators.UsernameValidator;
import com.practice.bank.model.*;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.FirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
public class UserController
{
    @Autowired
    private AccountService accountService;

    @Autowired
    private FirstNameService firstNameService;

    @GetMapping(value="/test")
    public Account test()
    {
        FirstName firstName=new FirstName();
        firstName.setName("Vladislav");

        firstNameService.addIfNotExists(firstName);

        Account account=new Account();
        account.setFirstName(firstName);
        account.setLastName("Sklyarov");

        accountService.addAccount(account);

        return account;
    }

    @GetMapping(value="/addaccount")
    public Account addAccount(@RequestParam(value="firstname")String firstName,
                           @RequestParam(value="lastname")String lastName,
                           @RequestParam(value="email")String email,
                           @RequestParam(value="code")String code,
                           @RequestParam(value="login")String login,
                           @RequestParam(value="password")String password)
    {
        if(validateUser(firstName,lastName,email,code,login,password))
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
    @GetMapping(value="/updateaccount")
    public Account updateAccount(@RequestParam(value="id") Long id,
                              @RequestParam(value="firstname")String firstName,
                              @RequestParam(value="lastname")String lastName,
                              @RequestParam(value="email")String email,
                              @RequestParam(value="code")String code,
                              @RequestParam(value="login")String login,
                              @RequestParam(value="password")String password)
    {
        Account account= accountService.getAccount(id);
        if(account!=null&&validateUser(firstName,lastName,email,code,login,password))
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
    @GetMapping(value="/removeaccount")
    public void removeAccount(@RequestParam(value="id")Long id)
    {
        accountService.removeAccount(id);
    }
    @GetMapping(value="/getaccount")
    public Set<Wallet> getAccount(@RequestParam(value = "id")Long id)
    {
        return accountService.getAccount(id).getWallets();
    }

    private boolean validateUser(String firstName,String lastName,String email,String code,String login,String password) {
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
