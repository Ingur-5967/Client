package ru.solomka.client.core.component.item.constant;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.ComponentBuilder;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.impl.MultiLabel;
import ru.solomka.client.core.component.item.impl.button.ModuleButton;
import ru.solomka.client.core.component.item.impl.pane.DefaultPane;
import ru.solomka.client.core.component.item.impl.pane.LinkedPane;
import ru.solomka.client.core.component.item.impl.VisualPaintComponent;
import ru.solomka.client.core.component.item.impl.button.ContextButton;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.event.impl.ChangeStateEvent;
import ru.solomka.client.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Test module
 */

public class ElementBarEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ElementBarEntry(@NotNull Pane root, CssContext[] properties) {
        this.container = new LinkedPane(160, (int) root.getPrefHeight(), "bottomBar");

        DefaultPane logoContainer = new DefaultPane(
                170, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER
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
                new VisualPaintComponent(30, 30, "icons/application-icon.png").load(),
                new MultiLabel(
                        new Pair<>(
                                ComponentBuilder.of(new Label()).css(
                                        new CssContext(CssProperties.FONT_SIZE.getProperty(15)),
                                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white"))).text("SaintClient"),
                                ComponentBuilder.of(new Label()).css(
                                        new CssContext(CssProperties.FONT_SIZE.getProperty(12)),
                                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty(ResourceConstant.Color.SUB_TEXT_BAR.getColor()))).text("Beta version")
                        ),
                        14,
                        new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent"))
                ).load()

        );

        logoContainer.getItem().setLayoutY(logoContainer.getItem().getLayoutY() + 10);

        ContextButton homeButton = new ContextButton(
                160, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, 14,
                new Pair<>("Главная", new CssContext[]{
                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
                        new CssContext(CssProperties.FONT_SIZE.getProperty(16))
                }),
                new Image(ResourceConstant.LOGO_BAR_HOME),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
        ).load();

        ContextButton productButton = new ContextButton(
                160, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, 14,
                new Pair<>("Товары", new CssContext[]{
                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
                        new CssContext(CssProperties.FONT_SIZE.getProperty(16))
                }),
                new Image(ResourceConstant.LOGO_BAR_PRODUCT),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
        ).load();

        ContextButton settingButton = new ContextButton(
                160, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, 14,
                new Pair<>("Настройки", new CssContext[]{
                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
                        new CssContext(CssProperties.FONT_SIZE.getProperty(16))
                }),
                new Image(ResourceConstant.LOGO_BAR_SETTING),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
        ).load();
        
        String username = "User32@mail.ru + negr";

        ModuleButton profileButton = new ModuleButton(
                160, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER - 4, 14,
                new MultiLabel(
                        new Pair<>(
                                ComponentBuilder.of(new Label()).css(
                                        new CssContext(CssProperties.FONT_SIZE.getProperty(14)),
                                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white"))).text((username.length() > 14 ? username.substring(0, 14).concat("...") : username)),
                                ComponentBuilder.of(new Label()).css(
                                        new CssContext(CssProperties.FONT_SIZE.getProperty(12)),
                                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("#0A5F38"))).text("Expired: 01.08.24")
                        ),
                        16,
                        new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
                ).load(),
                new Image(new File("D:\\Profile-test.png").getPath()),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
        ).load();

        AnchorPane initialPane = this.container.preInit(logoContainer, homeButton, productButton, settingButton, profileButton).getItem();

        for (int eIndex = 1; eIndex < initialPane.getChildren().size(); eIndex++) {

            if(eIndex == initialPane.getChildren().size() - 1) {
                container.get(eIndex).setLayoutY(this.container.getItem().getPrefHeight() - ((AnchorPane) container.get(eIndex)).getPrefHeight() - 10);
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