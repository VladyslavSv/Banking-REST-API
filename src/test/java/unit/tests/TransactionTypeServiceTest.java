package unit.tests;


import com.practice.bank.Application;
import com.practice.bank.model.TransactionType;
import com.practice.bank.services.TransactionTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransactionTypeServiceTest {


    @Autowired
    private TransactionTypeService typeService;

    @Test
    public void testAddTransactionType(){

        TransactionType type = new TransactionType();
        type.setName("TestOperation");

        type = typeService.addTransactionType(type);

        assertNotNull(type);
    }

    @Test
    public void testRemoveTransactionType(){
        Long id = 1L;

        TransactionType type = typeService.getTransactionTypeById(id);

        typeService.removeTransactionTypeById(id);

        type = typeService.getTransactionTypeById(id);

        assertNull(type);
    }

    @Test
    public void testUpdateTransactionType(){
        Long id = 2L;

        TransactionType type = typeService.getTransactionTypeById(id);
        type.setName("SomeType");

        type = typeService.updateTransactionType(type);

        assertNotNull(type);
    }

    @Test
    public void testGetTransactionType(){
        Long id = 2L;

        TransactionType type = typeService.getTransactionTypeById(id);

        assertNotNull(type);
    }
}
