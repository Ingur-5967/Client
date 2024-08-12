package ru.solomka.client.core.component.item.impl;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.file.utils.Resource;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VisualPaintComponent implements BaseComponent<VisualPaintComponent, AnchorPane> {

    private final AnchorPane container;
    private final ImageView viewer;

    public VisualPaintComponent(int width, int height, String url, CssContext ...properties) {
        this.container = new AnchorPane();
        this.container.setPrefSize(width, height);
        this.container.setStyle(CssContext.build(properties));

        Image image = new Image(Resource.getFileAsResource(url).getPath().substring(1));
        this.viewer = new ImageView(image);

        viewer.setFitWidth(width);
        viewer.setFitHeight(height);
    }


    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }

    @Override
    public VisualPaintComponent load() {
        this.container.getChildren().add(viewer);
        return this;
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }
}
