package com.practice.bank.dao;

import com.practice.bank.model.AtmTransaction;
import com.practice.bank.model.Wallet;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface ATMTransactionRepository extends PagingAndSortingRepository<AtmTransaction,Long> {
    List<AtmTransaction> findATMTransactionsByWallet(Wallet wallet);

}
