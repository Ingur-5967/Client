package ru.solomka.client.core.component.item.impl.pane;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.NoArgsConstructor;
import ru.solomka.client.core.component.item.LazyComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.SizeProperties;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.core.component.item.tag.Linked;
import ru.solomka.client.core.component.item.tag.enums.ComponentType;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.tool.Pair;
import ru.solomka.client.tool.functional.OperationSupplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedPane implements LazyComponent<LinkedPane, AnchorPane>, Linked, Container {

    private final AnchorPane container;
    private final List<SceneItem<?>> source;

    public LinkedPane(int weight, int height, String id, CssContext ...properties) {
        this.container = new AnchorPane();
        this.source = new ArrayList<>();
        container.setPrefSize(weight, height);
        container.setId(id);
        container.setStyle(CssContext.build(properties));
    }

    @Override
    public Node get(int position, ComponentType type) {
        List<Node> nodes = container.getChildren().stream().filter(c -> c.getClass().isInstance(type.getInstance())).toList();
        for(int index = 0; index < nodes.size(); index++) {
            if(index == position)
                return nodes.get(index);
        }
        return null;
    }

    @Override
    public Node get(int position) {
        return container.getChildren().get(position);
    }

    @Override
    public Node findFirst(ComponentType type) {
        return container.getChildren().getFirst();
    }

    @Override
    public Node findLast(ComponentType type) {
        return container.getChildren().getLast();
    }

    @Override
    public LinkedPane preInit(OperationSupplier<Pair<AnchorPane, List<SceneItem<?>>>> edit, SceneItem<?>... entries) {
        List<Node> remap = Arrays.stream(Arrays.stream(entries).map(SceneItem::getItem).toArray(Node[]::new)).toList();

        this.container.getChildren().addAll(
                (edit != null ? edit.operate(new Pair<>(this.container, Arrays.stream(entries).toList())).getSecond().stream().map(SceneItem::getItem).toList() : remap)
        );

        source.addAll(List.of(entries));

        return this;
    }

    @Override
    public LinkedPane preInit(SceneItem<?>... entries) {
        return this.preInit(null, entries);
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }

    @Override
    public void addChildren(SceneItem<?> item) {
        this.container.getChildren().add(item.getItem());
        this.source.add(item);
    }

    @Override
    public void addChildren(Node item) {
        this.getChildren().add(item);
    }

    @Override
    public List<Node> getChildren() {
        return this.container.getChildren();
    }

    @Override
    public List<SceneItem<?>> getSource() {
        return this.source;
    }

    @Override
    public SizeProperties getBounds() {
        return new SizeProperties(this.container.getPrefWidth(), this.container.getPrefHeight());
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }
}