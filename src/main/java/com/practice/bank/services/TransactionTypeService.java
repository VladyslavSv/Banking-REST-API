package com.practice.bank.services;

import com.practice.bank.dao.TransactionTypeRepository;
import com.practice.bank.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeService(){}

    public void addTransactionType(TransactionType type) {
        transactionTypeRepository.save(type);
    }

    public void removeTransactionTypeById(Long id) {
        transactionTypeRepository.deleteById(id);
    }

    public void updateTransactionType(TransactionType type) {
        transactionTypeRepository.save(type);
    }

    public TransactionType getTransactionTypeById(Long id) {
        return transactionTypeRepository.findById(id).get();
    }

}
