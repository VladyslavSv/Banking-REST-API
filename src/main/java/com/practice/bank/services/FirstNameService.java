package com.practice.bank.services;

import com.practice.bank.dao.FirstNameRepository;
import com.practice.bank.model.FirstName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstNameService {

    @Autowired
    private FirstNameRepository firstNameRepository;

    public FirstNameService() {}

    public void addIfNotExists(FirstName firstName) {
        FirstName tempName=firstNameRepository.findFirstNameByName(firstName.getName());
        if(tempName == null) {
            firstNameRepository.save(firstName);
        }
        else {
            firstName.setId(tempName.getId());
        }
    }

    public void remove(Long id) {
        firstNameRepository.deleteById(id);
    }

    public void change(FirstName firstName) {
        firstNameRepository.save(firstName);
    }

    public FirstName get(Long id) {
        return firstNameRepository.findById(id).get();
    }

}
