package model;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Application
{
    public static void main(String[] args)
    {
        //Create entities
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "db_bank" );
        EntityManager em = entityManagerFactory.createEntityManager();
        //begin transaction
        em.getTransaction().begin();

        //Create Wallet

        Wallet wallet=em.find(Wallet.class,4);

        ATMTransaction atmTransaction=new ATMTransaction();
        atmTransaction.setDate(new Date());
        atmTransaction.setSum(new BigDecimal(500));
        atmTransaction.setWallet(wallet);
        atmTransaction.setTransactionType(em.find(TransactionType.class,1));

        em.persist(atmTransaction);

        em.getTransaction().commit();
        //close resources
        em.close();
        entityManagerFactory.close();

    }
}
