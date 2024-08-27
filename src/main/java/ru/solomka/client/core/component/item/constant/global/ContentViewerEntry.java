package ru.solomka.client.core.component.item.constant.global;

import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.impl.text.InputField;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.tool.Pair;

public class ContentViewerEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ContentViewerEntry(@NotNull Pane region, @NotNull Container pointOf, int paddingLeft) {
        this.container = new LinkedPane(
                (int) (region.getPrefWidth() - paddingLeft - pointOf.getBounds().getWidth()), (int) region.getPrefHeight(), "contentViewer"
        ).initStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent")));

        this.container.setLocation(pointOf.getBounds().getWidth() + paddingLeft, 0);

        InputField search = new InputField(
                100, 30,
                new Pair<>("Введите ваш запрос", 12),
                new CssContext(CssProperties.PROMPT_TEXT_FILL_COLOR.getProperty("#989696")),
                new CssContext(CssProperties.TEXT_BOX_BORDER.getProperty("white")),
                new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
                new CssContext(CssProperties.FOCUS_COLOR.getProperty("transparent")),
                new CssContext(CssProperties.BACKGROUND_RADIUS.getProperty("7 7 7 7")),
                new CssContext(CssProperties.BORDER_RADIUS.getProperty("7px")),
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("#383360"))
        );

        search.setLocation(search.getItem().getLayoutX() + 20, search.getItem().getLayoutY() + 20);

        LinkedPane contextMenuEntry = new ContextMenuEntry(this.container.getItem(), 24, 7).build();

        this.container.addChildren(contextMenuEntry);
        this.container.addChildren(search);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}