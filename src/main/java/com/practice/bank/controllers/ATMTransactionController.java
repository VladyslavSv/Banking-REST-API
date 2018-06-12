package com.practice.bank.controllers;


import com.practice.bank.model.ATMTransaction;
import com.practice.bank.model.TransactionType;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.ATMTransactionService;
import com.practice.bank.services.TransactionTypeService;
import com.practice.bank.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
public class ATMTransactionController
{
    @Autowired
    private ATMTransactionService atmService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionTypeService transactionTypeService;

    @GetMapping(value = "/commitATMTransaction")
    public void commitTransaction(@RequestParam(value="walletid")Long walletId,
                                  @RequestParam(value="sum")BigDecimal sum,
                                  @RequestParam(value="transactionTypeId")Long transactionTypeId )
    {
        TransactionType type=transactionTypeService.getTransactionTypeById(transactionTypeId);
        Wallet wallet=walletService.getWalletById(walletId);

        atmService.commitTransaction(wallet,sum,type);
    }
    @GetMapping(value="/getAtmById")
    public ATMTransaction getTransactionById(@RequestParam(value="transactionId")Long transactionId)
    {
        return atmService.getAtmTransactionById(transactionId);
    }
    @GetMapping(value="/getAtmByWalletId")
    public List<ATMTransaction> getTransactionsByWalletId(@RequestParam(value="walletId")Long walletId)
    {
        Wallet wallet=walletService.getWalletById(walletId);
        return atmService.getTransactionsByWallet(wallet);
    }

}
