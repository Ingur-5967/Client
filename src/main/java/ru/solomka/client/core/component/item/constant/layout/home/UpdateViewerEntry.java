package ru.solomka.client.core.component.item.constant.layout.home;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ru.solomka.client.controller.http.UpdateHandler;
import ru.solomka.client.core.component.ComponentBuilder;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.impl.button.ImageButton;
import ru.solomka.client.core.component.item.impl.text.DefaultLabel;
import ru.solomka.client.core.component.item.impl.text.MultiLabel;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

public class UpdateViewerEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public UpdateViewerEntry(int width, int height, CssContext... properties) {
        this.container = new LinkedPane(width, height, "updateViewerEntry").initStyle(properties);

        AnchorPane topContainer = new AnchorPane();
        topContainer.setPrefSize(width, 20);

        DefaultLabel defaultUpdateContent = new DefaultLabel(
                "Обновления",
                new Pair<>(16, CssProperties.TEXT_FILL_COLOR.getProperty("white"))
        );

        ImageButton refreshUpdateContent = new ImageButton(20, 20, ItemAlignment.RIGHT, new Image(ResourceConstant.LOGO_REFRESH_CONTENT));


        refreshUpdateContent.setup((_, _) -> {
            try {
                refreshUpdateContent.animation(() -> {
                    RotateTransition anim = new RotateTransition(Duration.seconds(0.9), refreshUpdateContent.getItem());
                    anim.setFromAngle(0);
                    anim.setToAngle(-360);
                    anim.setInterpolator(Interpolator.LINEAR);
                    return anim;
                });
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });

        SceneItem<?> dataOfUpdate = UpdateHandler.getUpdateData().isEmpty()
                ? new DefaultLabel("No information of update", new Pair<>(12, CssProperties.TEXT_FILL_COLOR.getProperty("#989696")))
                : new LinkedPane(240, 30, "updateDownloadContainer").initStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("#383367")));

        defaultUpdateContent.setLocation(10, 7);
        refreshUpdateContent.setLocation(this.container.getBounds().getWidth() - refreshUpdateContent.getItem().getPrefWidth() - 7, refreshUpdateContent.getItem().getLayoutY() + 1);

        topContainer.getChildren().addAll(defaultUpdateContent.getItem(), refreshUpdateContent.getItem());

        topContainer.setLayoutX(0);
        topContainer.setLayoutY(5);

        if (dataOfUpdate instanceof LinkedPane infoUpdateContainer) {

            MultiLabel content = new MultiLabel(
                    ComponentBuilder.of(new Label())
                            .text("Версия beta-1.12.3")
                            .css(new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")), new CssContext(CssProperties.FONT_SIZE.getProperty(13)))
                            .create().getItem(),

                    ComponentBuilder.of(new Label())
                            .text("Время загрузки: 13:01:23 MSK")
                            .css(new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("#989696")), new CssContext(CssProperties.FONT_SIZE.getProperty(11)))
                            .create().getItem(), 12
            );

            ImageButton downloadButton = new ImageButton(25, 25, ItemAlignment.RIGHT, new Image(ResourceConstant.LOGO_DOWNLOAD));

            content.setLocation(3, WindowCalcHelper.getNegativeCentre(infoUpdateContainer.getItem(), content.getItem())[1]);

            infoUpdateContainer.addChildren(downloadButton);
            infoUpdateContainer.addChildren(content);

            downloadButton.setLocation(refreshUpdateContent.getItem().getLayoutX() - 2, content.getItem().getLayoutY());
        }

        dataOfUpdate.setLocation(
                (dataOfUpdate instanceof DefaultLabel ? WindowCalcHelper.getNegativeCentreOfLabel(this.container.getItem(), (Label) ((AnchorPane) dataOfUpdate.getItem()).getChildren().getFirst() )[0] : dataOfUpdate.getItem().getLayoutX()),
                (dataOfUpdate instanceof DefaultLabel ? defaultUpdateContent.getItem().getLayoutY() + 45 : defaultUpdateContent.getItem().getLayoutY() + 35)
        );

        this.container.addChildren(topContainer);
        this.container.addChildren(dataOfUpdate);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
