package com.practice.bank.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @NonNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @Getter
    @Setter
    @NonNull
    private Wallet sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @Getter
    @Setter
    @NonNull
    private Wallet receiver;

    @Getter
    @Setter
    @NonNull
    private Date date;

    @Getter
    @Setter
    private BigDecimal sumSent;

    @Getter
    @Setter
    private BigDecimal sumReceived;

}
