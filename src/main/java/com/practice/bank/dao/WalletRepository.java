package com.practice.bank.dao;

import com.practice.bank.model.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WalletRepository extends PagingAndSortingRepository<Wallet,Long>
{

}
