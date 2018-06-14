package com.practice.bank.dao;



import com.practice.bank.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account,Long>
{
    Account getAccountByLoginAndPassword(String login,String password);
}
