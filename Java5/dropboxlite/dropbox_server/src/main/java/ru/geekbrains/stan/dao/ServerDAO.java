package ru.geekbrains.stan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.stan.entity.User;

import java.util.List;

public class ServerDAO {


    public User readUser(String login, String password){

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        User user;
        try {


            session.beginTransaction();

            System.out.println(String.format("ServerDAO: %s %s creating query...", login, password));
            List<User> users = session.createQuery("from User u where u.login='"
                    + login + "' and u.password='" + password + "'").getResultList();
            if (users != null) System.out.println(users);

            if (users == null || users.size() != 1)
                user = null;
            else
                user = users.get(0);

            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
        return user;
    }

}
