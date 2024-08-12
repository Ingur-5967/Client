package ru.solomka.client.core.component.item.impl.pane;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ru.solomka.client.core.component.item.LazyComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.SizeProperties;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.tool.Pair;
import ru.solomka.client.tool.functional.OperationSupplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultPane implements LazyComponent<DefaultPane, AnchorPane>, Container {

    private final AnchorPane container;
    private final List<SceneItem<?>> source;

    public DefaultPane(int width, int height) {
        this.container = new AnchorPane();
        this.source = new ArrayList<>();
        this.container.setPrefSize(width, height);
    }

    @Override
    public DefaultPane preInit(OperationSupplier<Pair<AnchorPane, List<SceneItem<?>>>> edit, SceneItem<?>... entries) {
        List<Node> remap = Arrays.stream(Arrays.stream(entries).map(SceneItem::getItem).toArray(Node[]::new)).toList();

        this.container.getChildren().addAll(
            (edit != null ? edit.operate(new Pair<>(this.container, Arrays.stream(entries).toList())).getSecond().stream().map(SceneItem::getItem).toList() : remap)
        );

        source.addAll(List.of(entries));

        return this;
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }

    @Override
    public DefaultPane preInit(SceneItem<?>... entries) {
        return this.preInit(null, entries);
    }

    @Override
    public void addChildren(SceneItem<?> item) {
        this.container.getChildren().add(item.getItem());
        this.source.add(item);
    }

    @Override
    public void addChildren(Node item) {
        this.container.getChildren().add(item);
    }

    @Override
    public SizeProperties getBounds() {
        return new SizeProperties(this.container.getPrefWidth(), this.container.getPrefHeight());
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
    public List<SceneItem<?>> getSource() {
        return this.source;
    }
}