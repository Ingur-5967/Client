package ru.solomka.client.core.component.item.constant.layout.home;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
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

        ImageButton refreshUserList = new ImageButton(
                20, 20, ItemAlignment.RIGHT, new Image(ResourceConstant.LOGO_REFRESH_CONTENT)
        );

        refreshUserList.setup((_, _) -> {
            try {
                refreshUserList.animation(() -> {
                    RotateTransition anim = new RotateTransition(Duration.seconds(0.9), refreshUserList.getItem());
                    anim.setFromAngle(0);
                    anim.setToAngle(-360);
                    anim.setInterpolator(Interpolator.LINEAR);
                    return anim;
                });
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });

        topContainer.setPrefSize(width, refreshUserList.getItem().getPrefHeight());
        topContainer.setLayoutY(10);

        defaultFriendContent.setLocation(10, 5);
        refreshUserList.setLocation(this.container.getBounds().getWidth() - refreshUserList.getItem().getPrefWidth() - 7, refreshUserList.getItem().getLayoutY());

        topContainer.getChildren().addAll(defaultFriendContent.getItem(), refreshUserList.getItem());

        this.container.getChildren().add(topContainer);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
