package ru.solomka.client.core.component.item.impl.base;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.solomka.client.AppLoader;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.Interact;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.tool.Pair;

import java.util.function.BiConsumer;

public abstract class BaseButton implements BaseComponent<BaseButton, AnchorPane>, Interact {

    private final AnchorPane region;

    public BaseButton(String id) {
        this.region = new AnchorPane();
        this.region.setId(id);
    }

    @Override
    public <I extends SceneItem<AnchorPane>> I initStyle(CssContext... properties) {
        this.region.setStyle(CssContext.build(properties));
        return (I) this;
    }

    @Override
    public BaseButton load() { return this; }

    @Override
    public <E extends Event> void setup(BiConsumer<E, Pair<Stage, Node>> interact) {
        this.region.setOnMouseClicked(e -> interact.accept((E) e, new Pair<>(AppLoader.getPrimaryStage(), this.getItem())));
    }

    @Override
    public void setLocation(double x, double y) {
        this.region.setLayoutX(x);
        this.region.setLayoutY(y);
    }

    @Override
    public AnchorPane getItem() {
        return this.region;
    }
}