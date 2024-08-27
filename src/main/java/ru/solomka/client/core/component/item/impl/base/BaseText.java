package ru.solomka.client.core.component.item.impl.base;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.DataContext;
import ru.solomka.client.core.component.option.CssContext;

public class BaseText implements BaseComponent<BaseText, AnchorPane>, DataContext {

    private final AnchorPane container;
    private final Label text;

    public BaseText(String mainContent, int font) {
        this.container = new AnchorPane();
        this.text = new Label(mainContent);
        text.setFont(Font.font(font));
    }

    @Override
    public BaseText load() { return this; }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssContext... properties) {
        this.container.setStyle(CssContext.build(properties));
        return (I) this;
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }

    @Override
    public Object getData() {
        return this.text;
    }
}