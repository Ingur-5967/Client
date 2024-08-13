package ru.solomka.client.core.component.layout.impl;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.entity.SceneEntry;
import ru.solomka.client.core.component.item.constant.ContentViewerEntry;
import ru.solomka.client.core.component.item.constant.ContextMenuEntry;
import ru.solomka.client.core.component.item.constant.ElementBarEntry;
import ru.solomka.client.core.component.item.impl.pane.LinkedPane;
import ru.solomka.client.core.component.layout.Layout;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;

public class HomeLayout implements Layout {


    @Override
    public void loadLayout(Pane region, SceneEntry entry) {

        LinkedPane elementBarEntry = new ElementBarEntry(
                region,
                new CssContext[]{new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(ResourceConstant.Color.BACKGROUND_BAR.getColor())), new CssContext(CssProperties.BORDER_RADIUS.getProperty(0))}
        ).build();

        LinkedPane contentViewer = new ContentViewerEntry(region, elementBarEntry, 20).build();

        region.getChildren().addAll(elementBarEntry.getItem(), contentViewer.getItem());
    }
}