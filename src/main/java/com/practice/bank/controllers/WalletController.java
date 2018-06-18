package com.practice.bank.controllers;

import com.practice.bank.model.Account;
import com.practice.bank.model.Currency;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.CurrencyService;
import com.practice.bank.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
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
    @PutMapping(value="/addWalletToUser")
    public Wallet addWalletToUser(@RequestParam(value="accountId") Long accountId,
                                @RequestParam(value="currencyId")Long currencyId)
    {
        Currency currency=currencyService.getCurrencyByid(currencyId);
        Account account= accountService.getAccount(accountId);

        Stream<Wallet> walletStream=account.getWallets().stream();
        String currencyName=currency.getName();
        //if there is no wallets with the same currency
        if(walletStream.allMatch(innerWallet -> !innerWallet.getCurrency().getName().equals(currencyName)))
        {
            Wallet wallet=new Wallet();
            wallet.setAccount(account);
            wallet.setCurrency(currency);
            wallet.setAmount(new BigDecimal(0));

            walletService.addWallet(wallet);
            return wallet;
        }
        return null;
    }

    @DeleteMapping(value="/remove")
    public void removeWalletById(@RequestParam(value="id") Long id)
    {
        walletService.removeWalletById(id);
    }

    @GetMapping(value="/get")
    public Wallet getWallet(@RequestParam(value="id")Long id)
    {
        return walletService.getWalletById(id);
    }

    @GetMapping(value="/getAccountsWallets")
    public List<Wallet> getUsersWallets(@RequestParam(value = "accountId")Long accountId)
    {
        return walletService.getWalletsByAccount_Id(accountId);
    }
}
