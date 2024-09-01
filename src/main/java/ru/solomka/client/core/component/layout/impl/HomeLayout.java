package ru.solomka.client.core.component.layout.impl;

import javafx.scene.layout.Pane;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.entity.SceneEntry;
import ru.solomka.client.core.component.item.constant.global.ContentViewerEntry;
import ru.solomka.client.core.component.item.constant.global.ElementBarEntry;
import ru.solomka.client.core.component.item.constant.layout.home.FriendViewerEntry;
import ru.solomka.client.core.component.item.constant.layout.home.NewsViewerEntry;
import ru.solomka.client.core.component.item.constant.layout.home.UpdateViewerEntry;
import ru.solomka.client.core.component.item.impl.LinkedPane;
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

        LinkedPane updateViewerEntry = new UpdateViewerEntry(240, 85, new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("#383360"))).build();
        LinkedPane friendViewerEntry = new FriendViewerEntry(240, 300, new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("#383360"))).build();
        LinkedPane newsViewerEntry = new NewsViewerEntry(300, 365, new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent"))).build();

        friendViewerEntry.setLocation(20, 100);
        updateViewerEntry.setLocation(friendViewerEntry.getItem().getLayoutX(), friendViewerEntry.getBounds().getHeight() + updateViewerEntry.getBounds().getHeight() + 35);
        newsViewerEntry.setLocation(friendViewerEntry.getItem().getLayoutX() + 335,  100);

        contentViewer.addChildren(friendViewerEntry);
        contentViewer.addChildren(updateViewerEntry);
        contentViewer.addChildren(newsViewerEntry);

        region.getChildren().addAll(elementBarEntry.getItem(), contentViewer.getItem());
    }
}