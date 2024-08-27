package ru.solomka.client.core.component.item.impl.base;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.SizeProperties;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.core.component.option.CssContext;

import java.util.List;

public class BasePane implements Container, SceneItem<AnchorPane> {

    private final AnchorPane container;

    public BasePane(int width, int height, String id) {
        this.container = new AnchorPane();
        this.container.setPrefSize(width, height);
        this.container.setId(id);
    }

    @Override
    public void addChildren(Node item) {
        this.container.getChildren().add(item);
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssContext... properties) {
        this.container.setStyle(CssContext.build(properties));
        return (I) this;
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }

    @Override
    public List<Node> getChildren() {
        return this.container.getChildren();
    }

    @Override
    public SizeProperties getBounds() {
        return new SizeProperties(this.container.getPrefWidth(), this.container.getPrefHeight());
    }
}