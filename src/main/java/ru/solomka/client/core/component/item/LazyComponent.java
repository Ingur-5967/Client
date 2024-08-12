package ru.solomka.client.core.component.item;

import javafx.scene.Node;
import ru.solomka.client.tool.Pair;
import ru.solomka.client.tool.functional.OperationSupplier;

import java.util.List;

public interface LazyComponent<C extends LazyComponent<?, T>, T extends Node> extends SceneItem<T>  {
   C preInit(OperationSupplier<Pair<T, List<SceneItem<?>>>> edit, SceneItem<?> ...entries);
   C preInit(SceneItem<?> ...entries);
}
