package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
public class Account
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name= "count")
    private BigDecimal count;

    @Column(name= "currency")
    private String currency;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User owner;

    protected Account(){}

    public Account(BigDecimal count, String currency, User owner) {
        this.count = count;
        this.currency = currency;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
