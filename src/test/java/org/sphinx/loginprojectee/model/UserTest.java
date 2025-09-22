package org.sphinx.loginprojectee.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.sphinx.loginprojectee.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

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

}