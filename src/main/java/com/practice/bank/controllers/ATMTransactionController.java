package com.practice.bank.controllers;


import com.practice.bank.model.ATMTransaction;
import com.practice.bank.model.TransactionType;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.ATMTransactionService;
import com.practice.bank.services.TransactionTypeService;
import com.practice.bank.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/atms")
public class ATMTransactionController
{
    @Autowired
    private ATMTransactionService atmService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionTypeService transactionTypeService;

    @PostMapping(value = "/commit")
    public ATMTransaction commitTransaction(@RequestParam(value="transaction")ATMTransaction transaction) {
        return atmService.commitTransaction(transaction);
    }
    @GetMapping(value="/getById")
    public ATMTransaction getTransactionById(@RequestParam(value="transactionId")Long transactionId) {
        return atmService.getAtmTransactionById(transactionId);
    }
    @GetMapping(value="/getByWallet")
    public List<ATMTransaction> getTransactionsByWallet(@RequestParam(value="walletId")Wallet wallet) {
        return atmService.getTransactionsByWallet(wallet);
    }

}
