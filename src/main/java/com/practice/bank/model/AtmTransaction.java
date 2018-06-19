package com.practice.bank.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class AtmTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @NonNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    @Getter
    @Setter
    @NonNull
    private Wallet wallet;

    @Getter
    @Setter
    @NonNull
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "transactionType_id")
    @Getter
    @Setter
    @NonNull
    private TransactionType transactionType;

    @Getter
    @Setter
    private Date date;
}
