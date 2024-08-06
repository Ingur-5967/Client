package ru.solomka.client;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import ru.solomka.client.core.StageManager;
import ru.solomka.client.core.component.layout.Layout;

public abstract class AppLoader extends Application {

    @Getter private final String initFile;
    private final Layout initialLayout;

    public AppLoader(String initFile, Layout initialLayout) {
        this.initFile =  initFile;
        this.initialLayout = initialLayout;
    }

    @Override
    public void start(Stage stage) {
        StageManager.changeStage(null, initialLayout);
    }
}