package org.sphinx.loginprojectee.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sphinx.loginprojectee.dao.UserHibernateDAOImpl;
import org.sphinx.loginprojectee.exceptions.IncorrectPasswordException;
import org.sphinx.loginprojectee.exceptions.UserNotFoundException;
import org.sphinx.loginprojectee.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserHibernateDAOImplTest {
    public static UserHibernateDAOImpl userHibernateDAOImpl;
    @BeforeAll
    public static void init(){
        userHibernateDAOImpl = new UserHibernateDAOImpl();
    }


    @Test
    void testFetchByUsernameShouldReturnAliNejatiUser() throws UserNotFoundException, IncorrectPasswordException {
        User user = userHibernateDAOImpl.findUserByUsernameAndPassword("AliNejati",
                "123");

        assertEquals("Ali", user.getFirstName());
        assertEquals("Nejati", user.getLastName());
        assertEquals("AliNejati", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("aliNejati@gmail.com", user.getEmail());
    }
    @Test
    void testFetchByUsernameShouldThrowUserNotFoundException() throws UserNotFoundException, IncorrectPasswordException {
        assertThrows(UserNotFoundException.class, () -> userHibernateDAOImpl.findUserByUsernameAndPassword
                ("Naghi", "123"));
    }
    @Test
    void testFetchByUsernameShouldThrowIncorrectPasswordException() throws UserNotFoundException, IncorrectPasswordException {
        assertThrows(IncorrectPasswordException.class, () -> userHibernateDAOImpl.findUserByUsernameAndPassword
                ("SaraAh", "0000"));
    }
    @Test
    void addUserShouldAddNewUser() throws UserNotFoundException, IncorrectPasswordException {
        //deleting previous Datas in DB
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.createMutationQuery("delete from User").executeUpdate();
        transaction.commit();
        session.close();
        //inserting new user
        User user = new User("Ali", "Nejati", "AliNejati",
                "123", "aliNejati@gmail.com", Role.USER);
        userHibernateDAOImpl.addUser(user);
        //fetching user
        User user2 = userHibernateDAOImpl.findUserByUsernameAndPassword("AliNejati", "123");
        assertEquals("Ali", user2.getFirstName());
        assertEquals("Nejati", user2.getLastName());
        assertEquals("AliNejati", user2.getUsername());
        assertEquals("123", user2.getPassword());
        assertEquals("aliNejati@gmail.com", user2.getEmail());
    }

}
