package com.practice.bank.controllers;

import com.practice.bank.model.Currency;
import com.practice.bank.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value="/currencies")
public class CurrencyController
{
    @Autowired
    private CurrencyService currencyService;

    public CurrencyController(){}

    @PutMapping(value="/add")
    public Currency addCurrency(@RequestParam(value="name")String name, @RequestParam(value="rate")BigDecimal rate)
    {
        Currency currency=new Currency();
        currency.setName(name);
        currency.setRate(rate);

        currencyService.addCurrency(currency);
        return currency;
    }

    @DeleteMapping(value="/remove")
    public void removeCurrency(@RequestParam(value="id")Long id)
    {
        currencyService.removeCurrencyById(id);
    }

    @PostMapping(value="/update")
    public Currency updateCurrency(@RequestParam(value="id")Long id,BigDecimal rate)
    {
        Currency currency=currencyService.getCurrencyByid(id);
        currency.setRate(rate);

        currencyService.updateCurrency(currency);
        return  currency;
    }

    @GetMapping(value="/get")
    public Currency getCurrencyById(@RequestParam(value="id")Long id)
    {
        return currencyService.getCurrencyByid(id);
    }
}
