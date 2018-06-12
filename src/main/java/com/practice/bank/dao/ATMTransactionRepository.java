package com.practice.bank.dao;

import com.practice.bank.model.ATMTransaction;
import com.practice.bank.model.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;


public interface ATMTransactionRepository extends PagingAndSortingRepository<ATMTransaction,Long>
{
    List<ATMTransaction> findATMTransactionsByWallet(Wallet wallet);

}
