package com.practice.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "id")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @NonNull
    private Long id;

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @NonNull
    private BigDecimal rate;

    @OneToMany(mappedBy = "id")
    @JsonBackReference
    @Getter
    @Setter
    private Set<Wallet> wallets;

}
