package ru.solomka.client.core.component.item.impl.scroll;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.impl.button.ImageButton;
import ru.solomka.client.core.component.item.impl.text.DefaultLabel;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

public class ContentScroll extends BasePage {

    public ContentScroll(int width, int height, String id) {
        super(width, height, id);
    }

    @Override
    public void init() {

        ImageButton previous = new ImageButton(15, 15, ItemAlignment.LEFT, new Image(ResourceConstant.LOGO_PREVIOUS_ELEMENT));
        ImageButton next = new ImageButton(15, 15, ItemAlignment.RIGHT, new Image(ResourceConstant.LOGO_NEXT_ELEMENT));

        previous.setLocation(WindowCalcHelper.getNegativeCentre(this.getItem(), previous.getItem())[0] - 22, this.getItem().getPrefHeight() - 25);
        next.setLocation(WindowCalcHelper.getNegativeCentre(this.getItem(), previous.getItem())[0] + 22, this.getItem().getPrefHeight() - 25);

        DefaultLabel visualPage = new DefaultLabel(String.valueOf(this.getCurrentPage() + 1), new Pair<>(14, new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")).getCss()));

        visualPage.setLocation(previous.getItem().getLayoutX() + 25, previous.getItem().getLayoutY() + 3);

        AnchorPane viewer = new AnchorPane();

        viewer.getChildren().addAll(((Container) this.current()).getChildren());

        next.setup((_, _) -> {
            viewer.getChildren().addAll(((Container) this.next()).getChildren());


            if(this.hasNext()) {
                viewer.getChildren().clear();
                ((Label) visualPage.getData()).setText(String.valueOf(this.getCurrentPage() + 1));
            }

        });

        previous.setup((_, _) -> {
            viewer.getChildren().addAll(((Container) this.previous()).getChildren());


            if(this.hasNext()) {
                viewer.getChildren().clear();
                ((Label) visualPage.getData()).setText(String.valueOf(this.getCurrentPage()));
            }

        });

        this.getItem().getChildren().add(visualPage.getItem());
        this.getItem().getChildren().add(viewer);
        this.getItem().getChildren().add(next.getItem());
        this.getItem().getChildren().add(previous.getItem());
    }
}
