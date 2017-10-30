package ru.geekbrains.stan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.stan.entity.User;

import java.util.List;

public class ClientDAO {


    public User readUser(String login, String password){

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            List<User> users = session.createQuery("from User user where user.getLogin="
                    + login + " and user.getPassword=" + password).getResultList();
            if (users == null || users.size() != 1)
                return null;
            else
                return users.get(0);
        }
        finally {
            factory.close();
        }
    }

}
