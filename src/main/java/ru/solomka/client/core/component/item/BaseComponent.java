package ru.solomka.client.core.component.item;

import javafx.scene.Node;

public interface BaseComponent<C extends BaseComponent<?, T>, T extends Node> extends SceneItem<T> {
    C load();
}