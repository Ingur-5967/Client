package ru.solomka.client.core.component.item.constant;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.ComponentBuilder;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.impl.DefaultLabel;
import ru.solomka.client.core.component.item.impl.MultiLabel;
import ru.solomka.client.core.component.item.impl.pane.DefaultPane;
import ru.solomka.client.core.component.item.impl.pane.LinkedPane;
import ru.solomka.client.core.component.item.impl.VisualPaintComponent;
import ru.solomka.client.core.component.item.impl.button.ContextButton;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.core.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

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
                    labels.setLocation(image.getItem().getLayoutX() + 40, centreOfMultiLabel[1] - ((AnchorPane) labels.getItem()).getPrefHeight()/3.3);

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
                                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty(ResourceConstant.Color.SUB_TEXT_BAR.getColor()))).text("Always strong")
                        ),
                        14,
                        new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent"))
                ).load()

        );

        logoContainer.getItem().setLayoutY(logoContainer.getItem().getLayoutY() + 10);

        ContextButton entry1 = new ContextButton(
                160, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, 14,
                new Pair<>("Главная", new CssContext[]{
                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
                        new CssContext(CssProperties.FONT_SIZE.getProperty(16))
                }),
                new Image(ResourceConstant.LOGO_BAR_HOME),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
        ).load();

        ContextButton entry2 = new ContextButton(
                160, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, 14,
                new Pair<>("Товары", new CssContext[]{
                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
                        new CssContext(CssProperties.FONT_SIZE.getProperty(16))
                }),
                new Image(ResourceConstant.LOGO_BAR_PRODUCT),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
        ).load();

        ContextButton entry3 = new ContextButton(
                160, ResourceConstant.DEFAULT_SIZE_BUTTON_CONTAINER, 14,
                new Pair<>("Настройки", new CssContext[]{
                        new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
                        new CssContext(CssProperties.FONT_SIZE.getProperty(16))
                }),
                new Image(ResourceConstant.LOGO_BAR_SETTING),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(""))
        ).load();

        AnchorPane initialPane = this.container.preInit(logoContainer, entry1, entry2, entry3).getItem();

        for (int eIndex = 1; eIndex < initialPane.getChildren().size(); eIndex++) {
            container.get(eIndex).setLayoutY(container.get(eIndex - 1).getLayoutY() + (eIndex == 1 ? initialPane.getPrefHeight() / 3.5 : 65));
        }

        this.container.getAll().stream().skip(1).forEach(element -> {
            element.setOnMouseEntered(_ -> element.setStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(ResourceConstant.Color.ON_FOCUS_ELEMENT.getColor())).getCss()));
            element.setOnMouseExited(_ -> element.setStyle("-fx-background-color: transparent;"));
        });

        initialPane.setStyle(String.join(";", Arrays.stream(properties).map(CssContext::getCss).toList()));
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}