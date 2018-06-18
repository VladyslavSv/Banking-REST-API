package com.practice.bank.controllers;

import com.practice.bank.dao.WalletRepository;
import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
import com.practice.bank.services.WalletService;
import com.practice.bank.services.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value="/wTransactions")
public class WalletTransactionController
{
    @Autowired
    private WalletTransactionService walletTransactionService;
    @Autowired
    private WalletService walletService;

    @GetMapping(value="/getTransactions")
    @ResponseBody
    public List<WalletTransaction> getWalletTransactions(@RequestParam(value="walletId")Long walletId)
    {
        Wallet wallet=walletService.getWalletById(walletId);
        List<WalletTransaction> transactions = walletTransactionService.getWalletTransactions(wallet);
        return transactions;
    }

    @GetMapping(value="/getTransaction")
    @ResponseBody
    public WalletTransaction getWalletTransaction(@RequestParam(value="transactionId")Long id)
    {
        return walletTransactionService.getWalletTransaction(id);
    }

    @PostMapping(value="/commit")
    @ResponseBody
    public WalletTransaction commitTransaction(@RequestParam(value = "senderId")Long senderId,
                                  @RequestParam(value="receiverId")Long receiverId,
                                  @RequestParam(value="sum")BigDecimal sum)
    {
        Wallet sender=walletService.getWalletById(senderId);
        Wallet receiver=walletService.getWalletById(receiverId);

        return walletTransactionService.commitWalletTransaction(sender,receiver,sum);
    }
}
