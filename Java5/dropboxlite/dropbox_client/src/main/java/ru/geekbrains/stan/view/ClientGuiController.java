package ru.geekbrains.stan.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ru.geekbrains.stan.controller.ClientListener;
import ru.geekbrains.stan.controller.DropboxClient;

public class ClientGuiController implements ClientListener {

    @FXML
    TextField loginField;

    @FXML
    PasswordField passField;

    @FXML
    public VBox rootElement;

    private DropboxClient client = new DropboxClient(this);


    public void onLogin(ActionEvent actionEvent){
        System.out.println(loginField.getText() + " " + passField.getText());
    }

    public void onClientLog(String msg) {
        System.out.println(msg);
    }
}
