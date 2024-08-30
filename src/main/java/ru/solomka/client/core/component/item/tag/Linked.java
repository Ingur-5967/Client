package ru.solomka.client.core.component.item.tag;

import javafx.scene.Node;

public interface Linked {
    Node get(String id);
    Node get(int position);
}
