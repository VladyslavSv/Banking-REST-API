package com.practice.bank.services;

import com.practice.bank.dao.WalletRepository;
import com.practice.bank.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public WalletService(){}

    public Wallet addWallet(Wallet wallet) {
        walletRepository.save(wallet);

        return wallet;
    }

    public void removeWalletById(Long id) {
        walletRepository.deleteById(id);
    }

    public Wallet getWalletById(Long id) {
        Optional<Wallet> result=walletRepository.findById(id);

        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public List<Wallet> getWalletsByAccount_Id(Long id) {
        return walletRepository.getWalletsByAccount_Id(id);
    }

}
