package com.practice.bank.dao;

import com.practice.bank.model.Wallet;
import com.practice.bank.model.WalletTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface WalletTransactionRepository extends PagingAndSortingRepository<WalletTransaction,Long>
{
    List<WalletTransaction> findWalletTransactionsByReceiver(Wallet wallet);
}
