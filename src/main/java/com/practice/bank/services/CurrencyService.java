package com.practice.bank.services;

import com.practice.bank.dao.CurrencyRepository;
import com.practice.bank.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyService(){}

    public Currency addCurrency(Currency currency) {
        currencyRepository.save(currency);
        return currency;
    }

    public void removeCurrencyById(Long id) {
        currencyRepository.deleteById(id);
    }

    public Currency updateCurrency(Currency currency) {
        currencyRepository.save(currency);

        return currency;
    }

    public Currency getCurrencyByid(Long id) {
        Optional<Currency> result = currencyRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        } else {
            return null;
        }
    }

}
