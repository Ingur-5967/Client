package ru.solomka.client.core.component.item;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.option.CssContext;

public interface SceneItem<T extends Node> {

    default void init() {}

    <I extends SceneItem<T>> I initStyle(CssContext ...properties);

    void setLocation(double x, double y);

    T getItem();

    @NotNull
    static <N extends Node> SceneItem<N> fromSource(N source) {
        return new SceneItem<>() {
            @Override
            public <I extends SceneItem<N>> I initStyle(CssContext... properties) {
                Class<I> clazz = (Class<I>) source.getClass();

                Object instance;

                try {
                    instance = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                source.setStyle(CssContext.build(properties));

                return (I) instance;
            }

            @Override
            public void setLocation(double x, double y) {
                source.setLayoutX(x);
                source.setLayoutY(y);
            }

            @Override
            public N getItem() {
                return source;
            }
        };
    }
}