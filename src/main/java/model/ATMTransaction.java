package model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ATMTransaction
{
    public ATMTransaction(){}

    public ATMTransaction(Wallet wallet, BigDecimal sum, TransactionType transactionType, Date date) {
        this.wallet = wallet;
        this.sum = sum;
        this.transactionType = transactionType;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name="transactionType_id")
    private TransactionType transactionType;

    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
