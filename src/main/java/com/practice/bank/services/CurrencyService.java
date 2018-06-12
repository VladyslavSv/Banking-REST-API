package com.practice.bank.services;

import com.practice.bank.dao.CurrencyRepository;
import com.practice.bank.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService
{
    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyService(){}

    public void addCurrency(Currency currency)
    {
        currencyRepository.save(currency);
    }
    public void removeCurrencyById(Long id)
    {
        currencyRepository.deleteById(id);
    }
    public void updateCurrency(Currency currency)
    {
        currencyRepository.save(currency);
    }
    public Currency getCurrencyByid(Long id)
    {
        return currencyRepository.findById(id).get();
    }
}
