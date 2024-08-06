package ru.solomka.client.core.component.layout;

import javafx.scene.layout.Pane;
import ru.solomka.client.core.component.entity.SceneEntry;

public interface Layout {
    void loadLayout(Pane region, SceneEntry entry);
}