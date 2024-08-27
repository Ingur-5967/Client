package ru.solomka.client.core.component.item.constant.layout;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.impl.button.ImageButton;
import ru.solomka.client.core.component.item.impl.text.DefaultLabel;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.tool.Pair;

public class FriendViewerEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public FriendViewerEntry(int width, int height, CssContext ...properties) {
        this.container = new LinkedPane(width, height, "friendViewerEntry").initStyle(properties);

        AnchorPane topContainer = new AnchorPane();

        DefaultLabel defaultFriendContent = new DefaultLabel(
                "Пользователи", new Pair<>(16, CssProperties.TEXT_FILL_COLOR.getProperty("white"))
        );

        ImageButton button = new ImageButton(
                20, 20, ItemAlignment.RIGHT, new Image(ResourceConstant.LOGO_REFRESH_CONTENT)
        );

        topContainer.setPrefSize(width, button.getItem().getPrefHeight());
        topContainer.setLayoutY(10);

        defaultFriendContent.setLocation(10, 5);
        button.setLocation(this.container.getBounds().getWidth() - button.getItem().getPrefWidth() - 7, button.getItem().getLayoutY());

        topContainer.getChildren().addAll(defaultFriendContent.getItem(), button.getItem());


        this.container.getChildren().add(topContainer);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
