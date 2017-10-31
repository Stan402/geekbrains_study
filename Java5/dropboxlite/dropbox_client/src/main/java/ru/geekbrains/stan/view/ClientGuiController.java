package ru.geekbrains.stan.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
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
    TextArea textArea;

    @FXML
    public VBox rootElement;

    private DropboxClient client = new DropboxClient(this);


    public void onLogin(ActionEvent actionEvent){
//        String ipAddr = "192.168.0.30";
        String ipAddr = "195.47.192.77";
        int port = 8189;
        System.out.println(loginField.getText() + " " + passField.getText());
        client.connect(ipAddr, port);
    }

    public void onClientLog(String msg) {
        final String myMsg = msg + "\n";
        Platform.runLater(new Runnable() {
            public void run() {
                textArea.appendText(myMsg);
            }
        });
    }
}
