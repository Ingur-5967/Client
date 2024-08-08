package ru.solomka.client.core.component.item.constant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.impl.pane.LinkedPane;

import java.util.List;


public class ContextMenuEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ContextMenuEntry(Pane region, int horizontalSpace, int verticalSpace) {

        this.container = new LinkedPane(30 + horizontalSpace, 15, "contextMenu");

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

        this.container.getChildren().addAll(List.of(closeApp, rollUpApp));
        this.container.setLocation(region.getPrefWidth() - this.container.getItem().getPrefWidth(), this.container.getItem().getLayoutY() + verticalSpace);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
