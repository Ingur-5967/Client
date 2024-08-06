package ru.solomka.client.core.component.entity;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Supplier;

@Data @AllArgsConstructor
public class SceneElement {
    private final String id;
    private final Supplier<Node> node;
    private final Pane parent;
}