package ru.solomka.client.core.component.item.constant.layout.home;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ru.solomka.client.core.component.ComponentBuilder;
import ru.solomka.client.core.component.Padding;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.ImageViewer;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.impl.button.ContentButton;
import ru.solomka.client.core.component.item.impl.button.ImageButton;
import ru.solomka.client.core.component.item.impl.scroll.ContentScroll;
import ru.solomka.client.core.component.item.impl.text.DefaultLabel;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.tool.Pair;

import java.io.File;

public class FriendViewerEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public FriendViewerEntry(int width, int height, CssContext ...properties) {
        this.container = new LinkedPane(width, height, "friendViewerEntry").initStyle(properties);

        AnchorPane topContainer = new AnchorPane();

        DefaultLabel defaultFriendContent = new DefaultLabel(
                "Пользователи", new Pair<>(16, CssProperties.TEXT_FILL_COLOR.getProperty("white"))
        );
        defaultFriendContent.setLocation(10, 5);

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

            //todo visual refresh main friend content

        });

        refreshUserList.setLocation(this.container.getBounds().getWidth() - refreshUserList.getItem().getPrefWidth() - 7, refreshUserList.getItem().getLayoutY());

        topContainer.setPrefSize(width, refreshUserList.getItem().getPrefHeight());
        topContainer.setLayoutY(10);
        topContainer.getChildren().addAll(defaultFriendContent.getItem(), refreshUserList.getItem());

        //test view
        ContentScroll contentScroll = new ContentScroll((int) this.container.getBounds().getWidth(), (int) (this.container.getBounds().getHeight() - refreshUserList.getItem().getPrefHeight()*2 ), "friendScroll");
        LinkedPane friendContainer = new LinkedPane((int) this.container.getBounds().getWidth(), 50, "friendEntry").initStyle();

        contentScroll.setLocation(0, topContainer.getPrefHeight() * 2);

        for(int index = 0; index < 3; index++) {


            Image profileImage = ComponentBuilder.of(new ImageView(new File("D:\\Profile-test.png").getPath()))
                    .wrapper().size(20, 20)
                    .clip(25, 25)
                    .snapshot().get().getItem().getImage();


            ContentButton friendEntry = new ContentButton(
                    (int) this.container.getBounds().getWidth(), 50, "friendEntryButton", new ImageViewer(30, 30, ItemAlignment.LEFT, profileImage),
                    new DefaultLabel("User32Negr", new Pair<>(16, new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")).getCss())),
                    new Pair<>(new Padding(10,0,0, 0), new Padding(20,0,0,0))
            );

            if(index > 0)
                friendEntry.setLocation(0, friendContainer.get(index - 1).getLayoutY() + 45);

            friendContainer.addChildren(friendEntry);
        }


        contentScroll.setContent(
                friendContainer
        );

        contentScroll.init();

        this.container.getChildren().add(contentScroll.getItem());
        this.container.getChildren().add(topContainer);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
