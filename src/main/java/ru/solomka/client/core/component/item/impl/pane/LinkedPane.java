package ru.solomka.client.core.component.item.impl.pane;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.item.LazyComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.Linked;
import ru.solomka.client.core.component.item.tag.enums.ComponentType;
import ru.solomka.client.tool.Pair;
import ru.solomka.client.tool.functional.OperationSupplier;

import java.util.Arrays;
import java.util.List;

public class LinkedPane implements LazyComponent<LinkedPane, AnchorPane>, Linked {

    private final AnchorPane container;

    public LinkedPane(int weight, int height, String id) {
        this.container = new AnchorPane();
        container.setPrefSize(weight, height);
        container.setId(id);
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
    public List<Node> getAll() {
        return this.container.getChildren().stream().toList();
    }

    @Override
    public LinkedPane preInit(OperationSupplier<Pair<AnchorPane, List<SceneItem<?>>>> edit, SceneItem<?>... entries) {
        List<Node> remap = Arrays.stream(Arrays.stream(entries).map(SceneItem::getItem).toArray(Node[]::new)).toList();

        this.container.getChildren().addAll(
                (edit != null ? edit.operate(new Pair<>(this.container, Arrays.stream(entries).toList())).getSecond().stream().map(SceneItem::getItem).toList() : remap)
        );
        return this;
    }

    /**
     * Used by init AnchorPane
     *
     * @return init object of LinkedPane
     */

    @Override
    public LinkedPane preInit(SceneItem<?>... entries) {
        return this.preInit(null, entries);
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }

    /**
     * Get initial AnchorPane of LinkedPane
     *
     * @return already initial object of LinkedPane
     */

    @Override
    public AnchorPane getItem() {
        if(this.container.getChildren().isEmpty())
            return new AnchorPane();

        return this.container;
    }
}