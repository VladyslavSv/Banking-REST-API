package com.practice.bank.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
public class Account
{
    public Account(){}

    public Account(FirstName firstName, String lastName, String email, String code, String login, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.code = code;
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="firstName_id")
    private FirstName firstName;

    private String lastName;

    @Email
    private String email;

    private String code;

    private String login;

    private String password;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private Set<Wallet> wallets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName()
    {
        return firstName.getName()+" "+lastName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(Set<Wallet> wallets) {
        this.wallets = wallets;
    }
}
