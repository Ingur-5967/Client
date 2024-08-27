package ru.solomka.client.core.component.item.tag;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import ru.solomka.client.tool.Pair;

import java.util.function.BiConsumer;

public interface Interact {
  <E extends Event> void setup(BiConsumer<E, Pair<Stage, Node>> interact);

}
