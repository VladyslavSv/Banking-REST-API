package com.practice.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @NonNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    @Getter
    @Setter
    @NonNull
    private Account account;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Getter
    @Setter
    @NonNull
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_id")
    @JsonManagedReference
    @Getter
    @Setter
    @NonNull
    private Currency currency;

}
