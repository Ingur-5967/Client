package ru.solomka.client;

import lombok.Getter;
import ru.solomka.client.core.component.layout.impl.HomeLayout;

import java.lang.reflect.InvocationTargetException;

public class Client extends AppLoader {

    @Getter private static Client instance;

    public Client() { super("basic.fxml", new HomeLayout()); }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        instance = Client.class.getDeclaredConstructor().newInstance();
        launch(args);
    }
}