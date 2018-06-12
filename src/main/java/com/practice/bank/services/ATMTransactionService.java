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

    public void commitTransaction(Wallet wallet, BigDecimal sum, TransactionType type)
    {
        switch (type.getName())
        {
            case "Withdraw":
                if(wallet.getAmount().compareTo(sum)>0)
                {
                    wallet.setAmount(wallet.getAmount().remainder(sum));
                }
                break;
            case "Reliff":
                wallet.setAmount(wallet.getAmount().add(sum));
                break;
        }
        ATMTransaction transaction =new ATMTransaction();
        transaction.setDate(new Date());
        transaction.setSum(sum);
        transaction.setTransactionType(type);
        transaction.setWallet(wallet);

        atmTransactionRepository.save(transaction);
    }

    public ATMTransaction getAtmTransactionById(Long id)
    {
        return atmTransactionRepository.findById(id).get();
    }
    public List<ATMTransaction> getTransactionsByWallet(Wallet wallet)
    {
        return atmTransactionRepository.findATMTransactionsByWallet(wallet);
    }
}
