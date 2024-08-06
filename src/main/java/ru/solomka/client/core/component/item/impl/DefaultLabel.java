package ru.solomka.client.core.component.item.impl;

import javafx.scene.control.Label;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.option.CssContext;

import java.util.Arrays;

public class DefaultLabel implements BaseComponent<DefaultLabel, Label> {

    private final Label content;

    public DefaultLabel(String text, CssContext ...css) {
        this.content = new Label(text);
        this.content.setStyle(String.join(";", Arrays.stream(css).map(CssContext::getCss).toList()));
    }

    @Override
    public void setLocation(double x, double y) {
        this.content.setLayoutX(x);
        this.content.setLayoutY(y);
    }

    @Override
    public DefaultLabel load() {
        return this;
    }

    @Override
    public Label getItem() {
        return this.content;
    }
}