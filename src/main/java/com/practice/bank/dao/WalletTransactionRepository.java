package com.practice.bank.dao;

import com.practice.bank.model.WalletTransaction;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WalletTransactionRepository extends PagingAndSortingRepository<WalletTransaction,Long>
{
}
