package com.practice.bank.services;

import com.practice.bank.dao.FirstNameRepository;
import com.practice.bank.model.FirstName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FirstNameService {

    @Autowired
    private FirstNameRepository firstNameRepository;

    public FirstNameService() {}

    public FirstName addIfNotExists(FirstName firstName) {
        FirstName tempName = firstNameRepository.findFirstNameByName(firstName.getName());
        if(tempName == null) {
            firstNameRepository.save(firstName);
        } else {
            firstName.setId(tempName.getId());
        }
        return firstName;
    }

    public void remove(Long id) {
        firstNameRepository.deleteById(id);
    }

    public FirstName change(FirstName firstName) {
        firstNameRepository.save(firstName);
        return firstName;
    }

    public FirstName get(Long id) {
        Optional<FirstName> result = firstNameRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        } else {
            return null;
        }
    }

    public FirstNameRepository getFirstNameRepository() {
        return firstNameRepository;
    }

    public void setFirstNameRepository(FirstNameRepository firstNameRepository) {
        this.firstNameRepository = firstNameRepository;
    }
}
