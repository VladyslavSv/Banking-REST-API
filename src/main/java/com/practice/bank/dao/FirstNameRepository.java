package com.practice.bank.dao;

import com.practice.bank.model.FirstName;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FirstNameRepository extends PagingAndSortingRepository<FirstName,Long> {
}
