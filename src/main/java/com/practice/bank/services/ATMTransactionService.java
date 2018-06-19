package com.practice.bank.services;

import com.practice.bank.dao.ATMTransactionRepository;
import com.practice.bank.model.ATMTransaction;
import com.practice.bank.model.TransactionType;
import com.practice.bank.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ATMTransactionService
{
    @Autowired
    private ATMTransactionRepository atmTransactionRepository;

    public ATMTransactionService(){}

    public ATMTransaction commitTransaction(ATMTransaction transaction) {
        switch (transaction.getTransactionType().getName())
        {
            case "Withdraw":
                if(transaction.getWallet().getAmount().compareTo(transaction.getSum())>0)
                {
                    transaction.getWallet().setAmount(transaction.getWallet().getAmount().remainder(transaction.getSum()));
                }
                else
                {
                    return null;
                }
                break;
            case "Reliff":
                transaction.getWallet().setAmount(transaction.getWallet().getAmount().add(transaction.getSum()));
                break;
        }

        transaction.setDate(new Date());

        atmTransactionRepository.save(transaction);

        return transaction;
    }

    public ATMTransaction getAtmTransactionById(Long id) {
        return atmTransactionRepository.findById(id).get();
    }
    public List<ATMTransaction> getTransactionsByWallet(Wallet wallet) {
        return atmTransactionRepository.findATMTransactionsByWallet(wallet);
    }
}
