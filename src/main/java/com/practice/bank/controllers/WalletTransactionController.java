package com.practice.bank.controllers;

import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
import com.practice.bank.services.WalletService;
import com.practice.bank.services.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(value = "/wTransactions")
public class WalletTransactionController {
    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private WalletService walletService;

    @GetMapping(value = "/getTransactions")
    @ResponseBody
    public List<WalletTransaction> getWalletTransactions(@RequestParam(value = "walletId")Long walletId) {
        Wallet wallet = walletService.getWalletById(walletId);
        List<WalletTransaction> transactions = walletTransactionService.getWalletTransactions(wallet);
        return transactions;
    }

    @GetMapping(value = "/getTransaction")
    @ResponseBody
    public WalletTransaction getWalletTransaction(@RequestParam(value = "transactionId")Long id) {
        return walletTransactionService.getWalletTransaction(id);
    }

    @PostMapping(value = "/commit")
    @ResponseBody
    public WalletTransaction commitTransaction(@RequestParam(value = "transaction")WalletTransaction transaction) {
        return walletTransactionService.commitWalletTransaction(transaction);
    }
}
