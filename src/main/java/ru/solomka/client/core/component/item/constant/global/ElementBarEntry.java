package ru.solomka.client.core.component.item.constant.global;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.ComponentBuilder;
import ru.solomka.client.core.component.Padding;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.ImageViewer;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.impl.button.ContentButton;
import ru.solomka.client.core.component.item.impl.text.DefaultLabel;
import ru.solomka.client.core.component.item.impl.text.MultiLabel;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.event.impl.ChangeStateEvent;
import ru.solomka.client.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

import java.io.File;
import java.util.List;

public class ElementBarEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ElementBarEntry(@NotNull Pane root, CssContext[] properties) {
        this.container = new LinkedPane(160, (int) root.getPrefHeight(), "bottomBar");

        LinkedPane logoContainer = new LinkedPane(
                170, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, "appLogoContainer"
        ).preInit(
                operation -> {

                    AnchorPane parent = operation.getFirst();
                    List<SceneItem<?>> components = operation.getSecond();

                    SceneItem<?> image = components.getFirst();
                    SceneItem<?> labels = components.getLast();

                    double[] centreOfImageComponent = WindowCalcHelper.getNegativeCentre(parent, image.getItem());
                    double[] centreOfMultiLabel = WindowCalcHelper.getNegativeCentre(parent, labels.getItem());

                    image.setLocation(14, centreOfImageComponent[1]);
                    labels.setLocation(image.getItem().getLayoutX() + 40, centreOfMultiLabel[1] - ((AnchorPane) labels.getItem()).getPrefHeight() / 3.3);

                    return new Pair<>(parent, components);
                },
                new ImageViewer(30, 30, ItemAlignment.LEFT, new Image(ResourceConstant.LOGO_ICON)).load(),

                new MultiLabel(
                        ComponentBuilder.of(new Label())
                                .text("SaintClient").css(
                                        new CssContext(CssProperties.FONT_SIZE.getProperty(15)),
                                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white"))
                                ).create().getItem(),

                        ComponentBuilder.of(new Label())
                                .text("Beta version").css(
                                        new CssContext(CssProperties.FONT_SIZE.getProperty(12)),
                                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty(ResourceConstant.Color.SUB_TEXT_BAR.getColor()))).text("Beta version")
                                .create().getItem(), 14
                ).load()
        );

        logoContainer.getItem().setLayoutY(logoContainer.getItem().getLayoutY() + 10);

        ContentButton homeButton = new ContentButton(
                169, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, "homeButton", new ImageViewer(33, 33, ItemAlignment.LEFT, new Image(ResourceConstant.LOGO_BAR_HOME)),
                new DefaultLabel("Главная", new Pair<>(16, CssProperties.TEXT_FILL_COLOR.getProperty("white"))),
                new Pair<>(new Padding(12, 0, 0, 0), new Padding(20, 0,0, 0))
        ).initStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent")));

        ContentButton productButton = new ContentButton(
                169, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, "productButton", new ImageViewer(33, 33, ItemAlignment.LEFT, new Image(ResourceConstant.LOGO_BAR_PRODUCT)),
                new DefaultLabel("Сборки", new Pair<>(16, CssProperties.TEXT_FILL_COLOR.getProperty("white"))),
                new Pair<>(new Padding(12, 0, 0, 0), new Padding(20, 0,0, 0))
        ).initStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent")));

        ContentButton settingButton = new ContentButton(
                169, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, "settingButton", new ImageViewer(33, 33, ItemAlignment.LEFT, new Image(ResourceConstant.LOGO_BAR_SETTING)),
                new DefaultLabel("Настройки", new Pair<>(16, CssProperties.TEXT_FILL_COLOR.getProperty("white"))),
                new Pair<>(new Padding(12, 0, 0, 0), new Padding(20, 0,0, 0))
        ).initStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent")));

        //test profile block
        String username = "User32@mail.ru + negr";

        Image profileLogo = ComponentBuilder.of(new ImageView(new File("D:\\Profile-test.png").getPath()))
                                            .wrapper().size(25, 25)
                                            .clip(30, 30)
                                            .snapshot().get().getItem().getImage();

        ContentButton profileButton = new ContentButton(
                169, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, "profileButton", new ImageViewer(30, 30, ItemAlignment.LEFT, profileLogo),
                new MultiLabel(
                        ComponentBuilder.of(new Label()).css(
                                new CssContext(CssProperties.FONT_SIZE.getProperty(14)),
                                new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white"))).text((username.length() > 15 ? username.substring(0, 15).concat("...") : username))
                                .create().getItem(),
                        ComponentBuilder.of(new Label()).css(
                                new CssContext(CssProperties.FONT_SIZE.getProperty(12)),
                                new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("#0A5F38"))).text("Expired: 01.08.24")
                                .create().getItem(),
                        14
                ),
                new Pair<>(new Padding(5,0,9,0), new Padding(14,0,0,0))
        ).initStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent")));

        AnchorPane initialPane = this.container.preInit(logoContainer, homeButton, productButton, settingButton, profileButton).getItem();

        for (int eIndex = 1; eIndex < initialPane.getChildren().size(); eIndex++) {

            if(eIndex == initialPane.getChildren().size() - 1) {
                container.get(eIndex).setLayoutY(this.container.getItem().getPrefHeight() - ((AnchorPane) container.get(eIndex)).getPrefHeight() - 12);
                break;
            }
            container.get(eIndex).setLayoutY(container.get(eIndex - 1).getLayoutY() + (eIndex == 1 ? initialPane.getPrefHeight() / 3.5 : 65));
        }

        this.container.getSource().stream().skip(1).forEach(element -> {
            Node node = element.getItem();
            node.setOnMouseClicked(_ -> new ChangeStateEvent().onTriggeredEvent(this.container, element));
        });

        initialPane.setStyle(CssContext.build(properties));
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}