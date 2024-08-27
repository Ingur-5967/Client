package ru.solomka.client.core.component.item.constant.layout;

import javafx.scene.image.Image;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.constant.SceneModule;
import ru.solomka.client.core.component.item.impl.ImageViewer;
import ru.solomka.client.core.component.item.impl.LinkedPane;
import ru.solomka.client.core.component.item.impl.scroll.ImageScroll;
import ru.solomka.client.core.component.item.impl.text.DefaultLabel;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.tool.Pair;

public class NewsViewerEntry implements SceneModule<LinkedPane> {

    private final LinkedPane container;

    public NewsViewerEntry(int width, int height, CssContext ...properties) {
        this.container = new LinkedPane(width, height, "newsViewerEntry").initStyle(properties);

        this.container.getItem().setMaxHeight(height);

        DefaultLabel newsTitle = new DefaultLabel("Новости", new Pair<>(21, CssProperties.TEXT_FILL_COLOR.getProperty("white")));

        //Test add

        ImageScroll scrollingTool = new ImageScroll(width, height - 90, "newsList").initStyle(
                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent"))
        );

        scrollingTool.init();

        scrollingTool.setLocation(0, newsTitle.getItem().getLayoutY() + 25 );
        newsTitle.setLocation(0, 0);

        this.container.addChildren(scrollingTool);
        this.container.addChildren(newsTitle);
    }

    @Override
    public LinkedPane build() {
        return this.container;
    }
}
