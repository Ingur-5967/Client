package ru.solomka.client.core;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.entity.SceneElement;
import ru.solomka.client.core.component.entity.SceneEntry;
import ru.solomka.client.core.component.layout.Layout;

public class StageManager {

    public static void changeStage(Stage previous, Layout layout) {

        if (previous != null) previous.close();

        SceneEntry instance = new SceneEntry("basic.fxml");
        Scene scene = instance.initScene(922, 534);
        SceneElement aRoot = instance.findUnmodifiableElement(scene, a -> a.getId().equals("layout"));

        if(aRoot == null) return;
        AnchorPane pane = (AnchorPane) aRoot.getNode().get();

        layout.loadLayout(pane, instance);

        Stage stage = new Stage();
        stage.setTitle("SaintClient | Developer");
        stage.getIcons().add(new Image(ResourceConstant.LOGO_ICON));
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();
    }

    public SceneEntry getCurrentScene() {
        return null;
    }
}