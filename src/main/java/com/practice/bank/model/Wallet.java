package com.practice.bank.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Wallet
{
    public Wallet(){}

    public Wallet(Account accont, BigDecimal amount, Currency currency)
    {
        this.accont = accont;
        this.amount = amount;
        this.currency = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account accont;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="currency_id")
    private Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccont() {
        return accont;
    }

    public void setAccont(Account accont) {
        this.accont = accont;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", accont=" + accont +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
