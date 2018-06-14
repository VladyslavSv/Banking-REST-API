package com.practice.bank.controllers;

import com.practice.bank.model.Account;
import com.practice.bank.model.Currency;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.CurrencyService;
import com.practice.bank.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping(value="/wallets")
public class WalletController
{
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private WalletService walletService;

    public WalletController(){}
    @GetMapping(value="/addwallet")
    public Wallet addWalletToUser(@RequestParam(value="accountid") Long accountId,
                                @RequestParam(value="currencyid")Long currencyId)
    {
        Currency currency=currencyService.getCurrencyByid(currencyId);
        Account account= accountService.getAccount(accountId);

        Stream<Wallet> walletStream=account.getWallets().stream();
        String currencyName=currency.getName();
        //if there is no wallets with the same currency
        if(walletStream.allMatch(innerWallet -> !innerWallet.getCurrency().getName().equals(currencyName)))
        {
            Wallet wallet=new Wallet();
            wallet.setAccont(account);
            wallet.setCurrency(currency);
            wallet.setAmount(new BigDecimal(0));

            walletService.addWallet(wallet);
            return wallet;
        }
        return null;
    }

    @GetMapping(value="/removewallet")
    public void removeWalletById(@RequestParam(value="walletid") Long id)
    {
        walletService.removeWalletById(id);
    }

    @GetMapping(value="/getwallet")
    public Wallet getWallet(@RequestParam(value="walletid")Long id)
    {
        return walletService.getWalletById(id);
    }
}
