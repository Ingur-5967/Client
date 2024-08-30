package ru.solomka.client;

import lombok.Getter;
import ru.solomka.client.controller.http.ConnectionSocket;
import ru.solomka.client.core.component.layout.impl.HomeLayout;
import ru.solomka.client.file.FileHandler;
import ru.solomka.client.file.FileManager;
import ru.solomka.client.file.utils.Resource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Client extends AppLoader {

    @Getter private static Client instance;

    public Client() { super("basic.fxml", new HomeLayout()); }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        instance = Client.class.getDeclaredConstructor().newInstance();

        launch(args);
    }
}