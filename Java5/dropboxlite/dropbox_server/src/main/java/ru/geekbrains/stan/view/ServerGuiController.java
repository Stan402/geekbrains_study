package ru.geekbrains.stan.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import ru.geekbrains.stan.controller.DropboxServer;
import ru.geekbrains.stan.controller.ServerListener;

public class ServerGuiController implements ServerListener {

    private final static int DROPBOX_PORT = 8189;

    @FXML
    private TextArea textArea;

    @FXML
    public VBox rootElement;

    private DropboxServer server = new DropboxServer(this);

    public void onStartListening(ActionEvent actionEvent) {
        server.startListening(DROPBOX_PORT);
    }

    public void onDropAllClients(ActionEvent actionEvent) {

        server.dropAllClients();
    }

    public void onStopListening(ActionEvent actionEvent) {

        server.stopListening();
    }

    public void onServerLog(String msg){
        final String myMsg = msg + "\n";
        Platform.runLater(new Runnable() {
            public void run() {
                textArea.appendText(myMsg);
            }
        });

    }
}
