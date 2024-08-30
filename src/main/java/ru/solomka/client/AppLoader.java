package ru.solomka.client;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import ru.solomka.client.core.StageManager;
import ru.solomka.client.core.component.layout.Layout;

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
    }

    public static Stage getPrimaryStage() {
        return Objects.requireNonNull(PRIMARY_STAGE, "Scene is not loaded yet");
    }
}