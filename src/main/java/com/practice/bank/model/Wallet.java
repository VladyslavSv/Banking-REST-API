package com.practice.bank.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @Getter
    @Setter
    private Account account;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Getter
    @Setter
    @NonNull
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    @Getter
    @Setter
    @NonNull
    private Currency currency;

}
