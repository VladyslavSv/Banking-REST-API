package com.practice.bank.services;

import com.practice.bank.dao.ATMTransactionRepository;
import com.practice.bank.model.AtmTransaction;
import com.practice.bank.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AtmTransactionService {

    @Autowired
    private ATMTransactionRepository atmTransactionRepository;

    public AtmTransactionService(){}

    public AtmTransaction commitTransaction(AtmTransaction transaction) {
        switch (transaction.getTransactionType().getName()) {
            case "Withdraw":
                if(transaction.getWallet().getAmount().compareTo(transaction.getSum()) > 0) {
                    transaction.getWallet().setAmount(transaction.getWallet().getAmount().remainder(transaction.getSum()));
                } else {
                    return null;
                }
                break;
            case "Reliff":
                transaction.getWallet().setAmount(transaction.getWallet().getAmount().add(transaction.getSum()));
                break;
            default:
                break;
        }

        transaction.setDate(new Date());

        atmTransactionRepository.save(transaction);

        return transaction;
    }

    public AtmTransaction getAtmTransactionById(Long id) {
        Optional<AtmTransaction> result=atmTransactionRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public List<AtmTransaction> getTransactionsByWallet(Wallet wallet) {
        return atmTransactionRepository.findATMTransactionsByWallet(wallet);
    }

}
