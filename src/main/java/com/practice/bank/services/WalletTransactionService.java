package com.practice.bank.services;

import com.practice.bank.dao.WalletTransactionRepository;
import com.practice.bank.model.Currency;
import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WalletTransactionService
{
    @Autowired
    WalletTransactionRepository walletTransactionRepository;

    public WalletTransaction commitWalletTransaction(WalletTransaction transaction) {
        if(transaction.getSender().getAmount().compareTo(transaction.getSumSended())>=0)
        {
            //convert sender's currency to receiver's currency
            BigDecimal receivedSum = convert(transaction.getSumSended(), transaction.getSender().getCurrency(), transaction.getReceiver().getCurrency());
            //increase receiver's sum
            transaction.getReceiver().setAmount(transaction.getReceiver().getAmount().add(receivedSum));
            //decrease senders sum
            transaction.getSender().setAmount(transaction.getSender().getAmount().subtract(transaction.getSumSended()));
            //fill fields
            transaction.setSumReceived(receivedSum);
            transaction.setDate(new Date());
            //save transaction in database
            walletTransactionRepository.save(transaction);
            return transaction;
        }
        else
        {
            return null;
        }
    }
    public WalletTransaction getWalletTransaction(Long id) {
        return walletTransactionRepository.findById(id).get();
    }
    public List<WalletTransaction> getWalletTransactions(Wallet wallet) {
        return walletTransactionRepository.findWalletTransactionsByReceiver(wallet);
    }
    public BigDecimal convert(BigDecimal sumSended, Currency sendingCurrency,Currency receivingCurrency) {
        //divide on sender currency rate then multiply on receiver currency rate
        BigDecimal receivedSum = sumSended.divide(sendingCurrency.getRate()).multiply(receivingCurrency.getRate());
        return receivedSum;
    }
}
