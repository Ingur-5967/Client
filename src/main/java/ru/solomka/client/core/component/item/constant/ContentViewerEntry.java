package ru.solomka.client.core.component.item.constant;

import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.item.impl.DefaultLabel;
import ru.solomka.client.core.component.item.impl.pane.LinkedPane;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;

public class ContentViewerEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public ContentViewerEntry(@NotNull Pane region, @NotNull Container pointOf, int paddingLeft, boolean viewContextMenu) {
        this.container = new LinkedPane(
                (int) (region.getPrefWidth() - paddingLeft - pointOf.getBounds().getWidth()), (int) region.getPrefHeight(), "contentViewer", new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("blue"))
        );

        if(viewContextMenu) {
            LinkedPane contextMenuEntry = new ContextMenuEntry(5).build();
            this.container.addChildren(contextMenuEntry);
        }

        this.container.addChildren(new DefaultLabel("neg"));
        this.container.setLocation(pointOf.getBounds().getWidth() + paddingLeft, 0);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}