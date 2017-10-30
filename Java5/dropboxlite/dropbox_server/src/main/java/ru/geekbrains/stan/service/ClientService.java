package ru.geekbrains.stan.service;

import ru.geekbrains.stan.dao.ClientDAO;
import ru.geekbrains.stan.entity.User;

public class ClientService {


    private ClientDAO clientDAO = new ClientDAO();

    public User retrieveUser(String login, String password){

        return clientDAO.readUser(login, password);
    }


}
