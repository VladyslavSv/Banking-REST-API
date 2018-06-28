package unit.tests.services;

import com.practice.bank.dao.TransactionTypeRepository;
import com.practice.bank.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeService(){}

    public TransactionType addTransactionType(TransactionType type) {
        transactionTypeRepository.save(type);

        return type;
    }

    public void removeTransactionTypeById(Long id) {
        transactionTypeRepository.deleteById(id);
    }

    public TransactionType updateTransactionType(TransactionType type) {
        transactionTypeRepository.save(type);

        return type;
    }

    public TransactionType getTransactionTypeById(Long id) {
        Optional<TransactionType> optional = transactionTypeRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

}
