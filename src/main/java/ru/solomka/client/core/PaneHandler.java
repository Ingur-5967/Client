package ru.solomka.client.core;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.solomka.client.tool.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor @Getter
public class PaneHandler {

    private Region region;

    public Region initDragWindow(Stage stage) {
        double[] xOffset = new double[1];
        double[] yOffset = new double[1];

        region.setOnMousePressed(event -> {
            xOffset[0] = stage.getX() - event.getScreenX();
            yOffset[0] = stage.getY() - event.getScreenY();
        });

        region.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset[0]);
            stage.setY(event.getScreenY() + yOffset[0]);
        });
        return region;
    }
}