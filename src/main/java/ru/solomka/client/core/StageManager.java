package ru.solomka.client.core;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.Nullable;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.entity.SceneEntry;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.core.component.layout.Layout;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;

public class StageManager {

    @Nullable
    public static Stage changeStage(Stage previous, Layout layout) {

        if (previous != null) previous.close();

        SceneEntry instance = new SceneEntry("basic.fxml");
        Scene scene = instance.initScene(922, 534);

        LinkedPane canvas = ((LinkedPane) Container.fromSource(LinkedPane.class, new AnchorPane(), new Object[]{922, 534, "layout"})).initStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("#353055")));

        canvas.getSource().addAll(scene.getRoot().getChildrenUnmodifiable().stream().map(SceneItem::fromSource).toList());

        SceneItem<?> aRoot = instance.findElement(canvas, a -> a.getItem().getId().equals("layout"));

        if(aRoot == null) return null;

        AnchorPane pane = (AnchorPane) aRoot.getItem();

        layout.loadLayout(pane, instance);

        Stage stage = new Stage();
        stage.setTitle("SaintClient");
        stage.getIcons().add(new Image(ResourceConstant.LOGO_ICON));
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();

        return stage;
    }
}