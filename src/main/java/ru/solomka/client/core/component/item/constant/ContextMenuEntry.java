package ru.solomka.client.core.component.item.constant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.impl.pane.LinkedPane;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;

import java.util.List;


public class ContextMenuEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ContextMenuEntry(int horizontalSpace) {

        this.container = new LinkedPane(30 + horizontalSpace, 15, "contextMenu", new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("red")));

        ImageView closeApp = new ImageView(new Image(ResourceConstant.LOGO_CLOSE_APP));
        ImageView rollUpApp = new ImageView(new Image(ResourceConstant.LOGO_ROLL_UP_APP));

        closeApp.setFitHeight(15);
        closeApp.setFitWidth(15);

        rollUpApp.setFitWidth(15);
        rollUpApp.setFitHeight(15);

        closeApp.setLayoutX(0);
        closeApp.setLayoutY(0);

        rollUpApp.setLayoutX(closeApp.getLayoutX() + horizontalSpace);
        rollUpApp.setLayoutY(0);

        this.container.getChildren().addAll(List.of(rollUpApp, closeApp));
        this.container.setLocation(10, 19);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
