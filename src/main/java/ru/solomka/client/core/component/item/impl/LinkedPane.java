package ru.solomka.client.core.component.item.impl;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.item.LazyComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.impl.base.BasePane;
import ru.solomka.client.core.component.item.tag.Linked;
import ru.solomka.client.tool.Pair;
import ru.solomka.client.tool.functional.OperationSupplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedPane extends BasePane implements LazyComponent<LinkedPane, AnchorPane>, Linked {

    private final List<SceneItem<?>> source;

    public LinkedPane(int width, int height, String id) {
        super(width, height, id);
        this.source = new ArrayList<>();
    }

    @Override
    public LinkedPane preInit(OperationSupplier<Pair<AnchorPane, List<SceneItem<?>>>> edit, SceneItem<?>... entries) {
        List<Node> remap = Arrays.stream(Arrays.stream(entries).map(SceneItem::getItem).toArray(Node[]::new)).toList();

        AnchorPane parent = this.getItem();

        source.addAll(List.of(entries));

        parent.getChildren().addAll(
                (edit != null ? edit.operate(new Pair<>(parent, Arrays.stream(entries).toList())).getSecond().stream().map(SceneItem::getItem).toList() : remap)
        );

        return this;
    }

    @Override
    public void addChildren(@NotNull SceneItem<?> item) {
        this.source.add(item);
        this.getItem().getChildren().add(item.getItem());
    }

    @Override
    public LinkedPane preInit(SceneItem<?>... entries) {
        return this.preInit(null, entries);
    }

    @Override
    public List<SceneItem<?>> getSource() {
        return this.source;
    }

    @Override
    public Node get(String id) {
        return this.getItem().getChildren().stream().filter(node -> node.getId() != null && node.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Node get(int position) {
        return this.getItem().getChildren().get(position);
    }
}