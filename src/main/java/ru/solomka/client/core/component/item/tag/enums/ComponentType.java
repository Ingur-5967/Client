package ru.solomka.client.core.component.item.tag.enums;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

@Getter
public enum ComponentType {
    BUTTON(Button.class),
    ANCHOR_PANE(AnchorPane.class),
    IMAGE_VIEW(ImageView.class),
    LABEL(Label.class);

    private final Class<? extends Node> instance;

    ComponentType(Class<? extends Node> instance) {
        this.instance = instance;
    }
}