package ru.solomka.client.core.component.item.tag;

import javafx.scene.Node;
import ru.solomka.client.core.component.item.SceneItem;

import java.util.List;

public interface Page {

    void setContent(SceneItem<?> ...items);
    List<SceneItem<?>> getContent();

    <T extends SceneItem<? extends Node>> T current();
    <T extends SceneItem<? extends Node>> T next();
    <T extends SceneItem<? extends Node>> T previous();

    boolean hasNext();

    int getCurrentPage();
}