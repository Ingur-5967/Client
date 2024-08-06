package ru.solomka.client.core.component.item;

import javafx.scene.Node;

public interface SceneItem<T extends Node> {

    void setLocation(double x, double y);

    /**
     * Get based component of class
     */

    T getItem();
}