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

    public void commitWalletTransaction(Wallet sender,Wallet receiver,BigDecimal sendedSum)
    {
        if(sendedSum.compareTo(sender.getAmount())>0)
        {
            //convert sender's currency to receiver's currency
            BigDecimal receivedSum = convert(sendedSum, sender.getCurrency(), receiver.getCurrency());
            //increase receiver's sum
            receiver.setAmount(receiver.getAmount().add(receivedSum));
            //decrease senders sum
            sender.setAmount(sender.getAmount().remainder(sendedSum));

            //create wallet transaction
            WalletTransaction walletTransaction=new WalletTransaction();
            walletTransaction.setSumSended(sendedSum);
            walletTransaction.setSumReceived(receivedSum);
            walletTransaction.setSender(sender);
            walletTransaction.setReceiver(receiver);
            walletTransaction.setDate(new Date());
            //save transaction in database
            walletTransactionRepository.save(walletTransaction);
        }
    }
    public WalletTransaction getWalletTransaction(Long id)
    {
        return walletTransactionRepository.findById(id).get();
    }
    public List<WalletTransaction> getWalletTransactions(Wallet wallet)
    {
        return walletTransactionRepository.findWalletTransactionsByReceiver(wallet);
    }
    public BigDecimal convert(BigDecimal sumSended, Currency sendingCurrency,Currency receivingCurrency)
    {
        //divide on sender currency rate then multiply on receiver currency rate
        BigDecimal receivedSum = sumSended.divide(sendingCurrency.getRate()).multiply(receivingCurrency.getRate());
        return receivedSum;
    }
}
