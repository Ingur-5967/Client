package ru.solomka.client.core.component;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.math.WindowCalcHelper;

import java.util.Arrays;

public class ComponentBuilder<T extends Node> {

    private final T source;

    private ComponentBuilder(T source) {
        this.source = source;
    }

    public ComponentBuilder<T> css(CssContext ...css) {
        source.setStyle(String.join(";", Arrays.stream(css).map(CssContext::getCss).toList()));
        return this;
    }

    public ComponentBuilder<T> text(String content) {
        if(!(source instanceof Label))
            throw new IllegalArgumentException("Component must have a label");

        ((Label) source).setText(content);

        return this;
    }

    public T create() {
        return source;
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static <S extends Node> ComponentBuilder<S> of(S source) {
        return new ComponentBuilder<>(source);
    }

}