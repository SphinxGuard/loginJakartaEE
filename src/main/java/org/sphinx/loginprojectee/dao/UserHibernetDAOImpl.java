package org.sphinx.loginprojectee.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphinx.loginprojectee.exceptions.IncorrectPasswordException;
import org.sphinx.loginprojectee.model.User;
import org.sphinx.loginprojectee.util.HibernateUtil;
import org.sphinx.loginprojectee.exceptions.UserNotFoundException;

import java.util.List;

public class UserHibernetDAOImpl implements UserDAO {

    @Override
    public User findUserByUsernameAndPassword(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<User> users = session.createQuery("from User where username = :username")
                    .setParameter("username", username)
                    .list();

            if (users.isEmpty()) {
                throw new UserNotFoundException("username: " + username + " doesn't exist");
            }

            User user = users.get(0);

            if (!user.getPassword().equals(password)) {
                throw new IncorrectPasswordException("password is incorrect");
            }

            transaction.commit();
            return user;

        } finally {
            // Always close session, even if exception is thrown
            session.close();
        }
    }
}
