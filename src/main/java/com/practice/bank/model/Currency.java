package com.practice.bank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Currency
{

    public Currency(){}

    public Currency(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal rate;

    @OneToMany(mappedBy = "id")
    private Set<Wallet> wallets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
