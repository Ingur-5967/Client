package ru.solomka.client.core.component.item.constant.global;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.AppLoader;
import ru.solomka.client.core.component.Padding;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.impl.button.ImageButton;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;

import java.util.List;

public class ContextMenuEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ContextMenuEntry(@NotNull Pane parent, @NotNull Padding padding, int verticalSpace) {

        this.container = new LinkedPane(35 + padding.getLeft() + padding.getRight(), 18, "contextMenu");

        ImageButton closeAppIcon = new ImageButton(18, 18, ItemAlignment.LEFT, new Image(ResourceConstant.LOGO_CLOSE_APP));
        ImageButton rollUpAppIcon = new ImageButton(18, 17, ItemAlignment.RIGHT, new Image(ResourceConstant.LOGO_ROLL_UP_APP));

        closeAppIcon.setup((_, _) -> AppLoader.getPrimaryStage().close()); //todo: Terminate any process
        rollUpAppIcon.setup((_, _) -> AppLoader.getPrimaryStage().toBack());

        closeAppIcon.setLocation(0, 0);
        rollUpAppIcon.setLocation(closeAppIcon.getItem().getLayoutX() + padding.getLeft() + padding.getRight(), closeAppIcon.getItem().getLayoutY());

        this.container.getChildren().addAll(List.of(closeAppIcon.getItem(), rollUpAppIcon.getItem()));
        this.container.setLocation(parent.getPrefWidth() - this.container.getBounds().getWidth(), verticalSpace);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
