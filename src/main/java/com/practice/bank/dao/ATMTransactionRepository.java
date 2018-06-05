package com.practice.bank.dao;

import com.practice.bank.model.ATMTransaction;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ATMTransactionRepository extends PagingAndSortingRepository<ATMTransaction,Long>
{

}
