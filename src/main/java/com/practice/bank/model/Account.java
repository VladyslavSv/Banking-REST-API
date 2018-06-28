package com.practice.bank.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id",
        scope     = Long.class)
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
    @Getter
    @Setter
    @ToString.Exclude
    private Set<Wallet> wallets;

    public String getFullName() {
        return firstName.getName() + " " + lastName;
    }
}
