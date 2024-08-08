package ru.solomka.client.core.component.item.tag;

import javafx.scene.Node;
import ru.solomka.client.core.component.item.SceneItem;

import java.util.List;

public interface Container {
    List<Node> getChildren();
    List<SceneItem<?>> getSource();
}