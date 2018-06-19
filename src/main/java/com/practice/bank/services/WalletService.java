package com.practice.bank.services;

import com.practice.bank.dao.WalletRepository;
import com.practice.bank.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public WalletService(){}

    public void addWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    public void removeWalletById(Long id) {
        walletRepository.deleteById(id);
    }

    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).get();
    }

    public List<Wallet> getWalletsByAccount_Id(Long id) {
        return walletRepository.getWalletsByAccount_Id(id);
    }

}
