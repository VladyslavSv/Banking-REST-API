package com.practice.bank.dao;

import com.practice.bank.model.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CurrencyRepository extends PagingAndSortingRepository<Currency,Long>
{

}
