package ru.solomka.client;

import lombok.Getter;
import ru.solomka.client.core.component.layout.impl.HomeLayout;
import ru.solomka.client.core.event.EventManager;
import ru.solomka.client.core.event.impl.ChangeStageEvent;

import java.lang.reflect.InvocationTargetException;

public class Client extends AppLoader {

    @Getter private static Client instance;

    public Client() { super("basic.fxml", new HomeLayout()); }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        instance = Client.class.getDeclaredConstructor().newInstance();
        EventManager.initEvents(new ChangeStageEvent());

        launch(args);
    }
}