package ru.solomka.client.core.component;

import javafx.scene.Node;

import java.util.List;

public interface Configuration<T> {
    <D, R extends Node> R preInit(D obj, T ...args);
    List<T> resizeComponents(double weight, double height, T ...args);
}
