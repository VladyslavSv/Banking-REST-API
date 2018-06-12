package com.practice.bank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class WalletTransaction
{
    public WalletTransaction(){}

    public WalletTransaction(Wallet sender, Wallet receiver, Date date, BigDecimal sumSended, BigDecimal sumReceived) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.sumSended = sumSended;
        this.sumReceived = sumReceived;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="sender_id")
    private Wallet sender;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private Wallet receiver;

    private Date date;

    private BigDecimal sumSended;

    private BigDecimal sumReceived;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getSender() {
        return sender;
    }

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public Wallet getReceiver() {
        return receiver;
    }

    public void setReceiver(Wallet receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSumSended() {
        return sumSended;
    }

    public void setSumSended(BigDecimal sumSended) {
        this.sumSended = sumSended;
    }

    public BigDecimal getSumReceived() {
        return sumReceived;
    }

    public void setSumReceived(BigDecimal sumReceived) {
        this.sumReceived = sumReceived;
    }
}
