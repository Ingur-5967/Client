package ru.solomka.client.core.component.item.tag;

import javafx.scene.Node;

public interface Changed<T> {
    void change(T newState);
    T getState();
}
