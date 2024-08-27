package ru.solomka.client.core.component.item.constant.global;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ru.solomka.client.AppLoader;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.LinkedPane;

import java.util.List;

public class ContextMenuEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ContextMenuEntry(Pane parent, int horizontalSpace, int verticalSpace) {

        this.container = new LinkedPane(35 + horizontalSpace, 18, "contextMenu");

        ImageView closeApp = new ImageView(new Image(ResourceConstant.LOGO_CLOSE_APP));
        ImageView rollUpApp = new ImageView(new Image(ResourceConstant.LOGO_ROLL_UP_APP));

        closeApp.setFitHeight(18);
        closeApp.setFitWidth(18);

        rollUpApp.setFitWidth(17);
        rollUpApp.setFitHeight(17);

        AnchorPane closeAppWrapper = new AnchorPane(closeApp);
        AnchorPane rollUpWrapper = new AnchorPane(rollUpApp);

        closeAppWrapper.setLayoutX(0);
        closeAppWrapper.setLayoutY(0);

        rollUpWrapper.setLayoutX(closeApp.getLayoutX() + horizontalSpace);
        rollUpWrapper.setLayoutY(0);

        closeAppWrapper.setOnMouseClicked(_ -> AppLoader.getPrimaryStage().close()); // todo: terminate any process
        rollUpWrapper.setOnMouseClicked(_ -> AppLoader.getPrimaryStage().toBack());

        this.container.getChildren().addAll(List.of(closeAppWrapper, rollUpWrapper));
        this.container.setLocation(parent.getPrefWidth() - this.container.getBounds().getWidth(), verticalSpace);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
