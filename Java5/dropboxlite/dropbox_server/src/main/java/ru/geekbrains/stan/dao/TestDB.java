package ru.geekbrains.stan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.stan.entity.User;

public class TestDB {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a user object
            System.out.println("Creating new user object...");
            User tempUser = new User("Paul", "PaulPass");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the user...");
            session.save(tempUser);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }


}
