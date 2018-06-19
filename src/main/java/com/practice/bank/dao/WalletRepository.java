package com.practice.bank.dao;

import com.practice.bank.model.Wallet;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface WalletRepository extends PagingAndSortingRepository<Wallet,Long> {
    List<Wallet> getWalletsByAccount_Id(Long id);
}
