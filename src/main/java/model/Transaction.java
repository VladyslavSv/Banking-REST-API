package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int count;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "fk_account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "fk_transactionType")
    private TransactionType transactionType;

    protected Transaction(){}

    public Transaction(int count, Date date, Account account, TransactionType transactionType) {
        this.count = count;
        this.date = date;
        this.account = account;
        this.transactionType = transactionType;
    }

}
