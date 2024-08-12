package ru.solomka.client.core.component.item.tag;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.item.LazyComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.SizeProperties;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public interface Container {

    void addChildren(SceneItem<?> item);
    void addChildren(Node item);

    List<Node> getChildren();
    List<SceneItem<?>> getSource();
    SizeProperties getBounds();

    @SuppressWarnings("unchecked")
    static <I extends Container> Container fromSource(Class<I> instance, Parent region, Object[] properties) {
        I loader;
        try {
            loader = (I) instance.getDeclaredConstructors()[0].newInstance(properties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        List<SceneItem<?>> items = region.getChildrenUnmodifiable().stream().filter(c -> c instanceof AnchorPane).map(node -> new SceneItem<>() {
            @Override
            public void setLocation(double x, double y) {
                node.setLayoutX(x);
                node.setLayoutY(y);
            }

            @Override
            public Node getItem() {
                return node;
            }
        }).collect(Collectors.toList());

        return items.isEmpty() ? loader : ((LazyComponent<? extends Container, ? extends Node>) loader).preInit(items.toArray(SceneItem[]::new));
    }
}