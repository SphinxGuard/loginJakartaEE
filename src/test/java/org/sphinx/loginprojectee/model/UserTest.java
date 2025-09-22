package org.sphinx.loginprojectee.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sphinx.loginprojectee.dao.UserHibernetDAOImpl;
import org.sphinx.loginprojectee.exceptions.UserNotFoundException;
import org.sphinx.loginprojectee.util.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @BeforeAll
    public static void setupDatabase(){
        // create 5 test users
        User ali = new User("Ali", "Nejati", "AliNejati", "123", "aliNejati@gmail.com");
        User sara = new User("Sara", "Ahmadi", "SaraAh", "123", "sara@gmail.com");
        User reza = new User("Reza", "Karimi", "RezaK", "123", "reza@gmail.com");
        User neda = new User("Neda", "Shahri", "NedaS", "123", "neda@gmail.com");
        User omid = new User("Omid", "Moradi", "OmidM", "123", "omid@gmail.com");
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        //deleting previous Datas in DB
        session.createQuery("delete from User").executeUpdate();
        //inserting managed datas in DB
        session.persist(ali);
        session.persist(sara);
        session.persist(reza);
        session.persist(neda);
        session.persist(omid);
        transaction.commit();
        session.close();
    }


    @Test
    void testUser() {
        User user = new User("Ali", "Nejati", "AliNejati",
                "123", "aliNejati@gmail.com");
        assertEquals("Ali", user.getFirstName());
        assertEquals("Nejati", user.getLastName());
        assertEquals("AliNejati", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("aliNejati@gmail.com", user.getEmail());
    }
    @Test
    void testSessionFactory(){
        User ali = new User("Ali", "Nejati", "AliNejati",
                "123", "aliNejati@gmail.com");

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ali);
        transaction.commit();
        session.close();

    }
    @Test
    void testFetchingIdOne(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<User> users = session.createQuery("from User").list();
        User user = (User) users.get(0);
        transaction.commit();

        session.close();

        assertEquals("Ali", user.getFirstName());
        assertEquals("Nejati", user.getLastName());
        assertEquals("AliNejati", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("aliNejati@gmail.com", user.getEmail());
    }
    @Test
    void testFetchByUsernameShouldReturnAliNejatiUser() throws UserNotFoundException {
        UserHibernetDAOImpl userHibernetDAOImpl = new UserHibernetDAOImpl();
        User user = userHibernetDAOImpl.findUserByUsernameAndPassword("AliNejati", "123");
        assertEquals("Ali", user.getFirstName());
        assertEquals("Nejati", user.getLastName());
        assertEquals("AliNejati", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("aliNejati@gmail.com", user.getEmail());
    }

}