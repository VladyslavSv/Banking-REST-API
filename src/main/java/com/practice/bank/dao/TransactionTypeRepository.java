package com.practice.bank.dao;

import com.practice.bank.model.TransactionType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionTypeRepository extends PagingAndSortingRepository<TransactionType,Long>
{
}
