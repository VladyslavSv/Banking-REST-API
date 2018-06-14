package com.practice.bank.controllers;

import com.practice.bank.dao.WalletRepository;
import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
import com.practice.bank.services.WalletService;
import com.practice.bank.services.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public List<WalletTransaction> getWalletTransactions(@RequestParam(value="walletid")Long walletId)
    {
        Wallet wallet=walletService.getWalletById(walletId);
        List<WalletTransaction> transactions = walletTransactionService.getWalletTransactions(wallet);
        return transactions;
    }

    @GetMapping(value="/getTransaction")
    @ResponseBody
    public WalletTransaction getWalletTransaction(@RequestParam(value="transactionid")Long id)
    {
        return walletTransactionService.getWalletTransaction(id);
    }

    @GetMapping(value="/commit")
    @ResponseBody
    public void commitTransaction(@RequestParam(value = "senderid")Long senderId,
                                  @RequestParam(value="receiverid")Long receiverId,
                                  @RequestParam(value="sum")BigDecimal sum)
    {
        Wallet sender=walletService.getWalletById(senderId);
        Wallet receiver=walletService.getWalletById(receiverId);

        walletTransactionService.commitWalletTransaction(sender,receiver,sum);
    }
}
