package ru.solomka.client;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import ru.solomka.client.controller.http.ConnectionSocket;
import ru.solomka.client.core.StageManager;
import ru.solomka.client.core.component.layout.Layout;

import java.io.IOException;
import java.util.Objects;

public abstract class AppLoader extends Application {

    @Getter private final String initFile;
    private final Layout initialLayout;

    private static Stage PRIMARY_STAGE;

    public AppLoader(String initFile, Layout initialLayout) {
        this.initFile =  initFile;
        this.initialLayout = initialLayout;
    }

    @Override
    public void start(Stage stage) {
        PRIMARY_STAGE = StageManager.changeStage(null, initialLayout);

//        ConnectionSocket socket = new ConnectionSocket("localhost", 4004);
//
//        ConnectionSocket.Server server = socket.server();
//        ConnectionSocket.Client client = socket.client();
//
//        server.await();
//        try {
//            client.listen();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    public static Stage getPrimaryStage() {
        return Objects.requireNonNull(PRIMARY_STAGE, "Scene is not loaded yet");
    }
}