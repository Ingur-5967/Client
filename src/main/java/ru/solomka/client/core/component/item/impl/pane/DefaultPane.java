package ru.solomka.client.core.component.item.impl.pane;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.item.LazyComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.tool.Pair;
import ru.solomka.client.tool.functional.OperationSupplier;

import java.util.Arrays;
import java.util.List;

public class DefaultPane implements LazyComponent<DefaultPane, AnchorPane> {

    private final AnchorPane container;

    public DefaultPane(int width, int height) {
        this.container = new AnchorPane();
        this.container.setPrefSize(width, height);
    }

    @Override
    public DefaultPane preInit(OperationSupplier<Pair<AnchorPane, List<SceneItem<?>>>> edit, SceneItem<?>... entries) {
        List<Node> remap = Arrays.stream(Arrays.stream(entries).map(SceneItem::getItem).toArray(Node[]::new)).toList();

        this.container.getChildren().addAll(
            (edit != null ? edit.operate(new Pair<>(this.container, Arrays.stream(entries).toList())).getSecond().stream().map(SceneItem::getItem).toList() : remap)
        );
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
    public AnchorPane getItem() {
        return this.container;
    }
}