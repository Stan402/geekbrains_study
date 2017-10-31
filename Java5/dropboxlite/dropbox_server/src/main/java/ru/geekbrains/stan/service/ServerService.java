package ru.geekbrains.stan.service;

import ru.geekbrains.stan.dao.ServerDAO;
import ru.geekbrains.stan.entity.User;

public class ServerService {


    private ServerDAO serverDAO = new ServerDAO();

    public User retrieveUser(String login, String password){

        System.out.println(String.format("ServerService: %s %s", login, password));
        return serverDAO.readUser(login, password);

    }


}
