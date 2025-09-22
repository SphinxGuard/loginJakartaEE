package org.sphinx.loginprojectee.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphinx.loginprojectee.model.User;
import org.sphinx.loginprojectee.util.HibernateUtil;

public class UserHibernetDAOImpl implements UserDAO {

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("from User where username = :username").setParameter("username", username).list();


        session.close();
        return  null;
    }
}
