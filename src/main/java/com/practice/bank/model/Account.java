package com.practice.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @ToString.Exclude
    private Long id;

    @ManyToOne
    @JoinColumn(name="firstName_id")
    @Getter
    @Setter
    @NonNull
    private FirstName firstName;

    @Getter
    @Setter
    @NonNull
    private String lastName;

    @Email
    @Getter
    @Setter
    @NonNull
    private String email;

    @Getter
    @Setter
    @NonNull
    private String code;

    @Getter
    @Setter
    @NonNull
    private String login;

    @Getter
    @Setter
    @NonNull
    private String password;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    @JsonBackReference
    @Getter
    @Setter
    @ToString.Exclude
    private Set<Wallet> wallets;

    public String getFullName() {
        return firstName.getName()+" "+lastName;
    }
}
