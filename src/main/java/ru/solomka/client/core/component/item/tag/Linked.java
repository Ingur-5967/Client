package ru.solomka.client.core.component.item.tag;

import javafx.scene.Node;
import ru.solomka.client.core.component.item.tag.enums.ComponentType;

import java.util.List;

public interface Linked {
    Node get(int position);
    Node get(int position, ComponentType type);
    Node findFirst(ComponentType type);
    Node findLast(ComponentType type);
    List<Node> getAll();
}
