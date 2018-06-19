package com.practice.bank.controllers;

import com.practice.bank.model.AtmTransaction;
import com.practice.bank.model.Wallet;
import com.practice.bank.services.AtmTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/atms")
public class AtmTransactionController {
    @Autowired
    private AtmTransactionService atmService;

    @PostMapping(value = "/commit")
    public AtmTransaction commitTransaction(@RequestParam(value = "transaction") AtmTransaction transaction) {
        return atmService.commitTransaction(transaction);
    }

    @GetMapping(value = "/getById")
    public AtmTransaction getTransactionById(@RequestParam(value = "transactionId") Long transactionId) {
        return atmService.getAtmTransactionById(transactionId);
    }

    @GetMapping(value = "/getByWallet")
    public List<AtmTransaction> getTransactionsByWallet(@RequestParam(value = "walletId") Wallet wallet) {
        return atmService.getTransactionsByWallet(wallet);
    }

}
