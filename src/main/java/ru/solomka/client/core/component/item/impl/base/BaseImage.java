package ru.solomka.client.core.component.item.impl.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.DataContext;
import ru.solomka.client.core.component.item.tag.ItemAnimation;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.core.component.option.CssContext;

public class BaseImage implements BaseComponent<BaseImage, AnchorPane>, ItemAnimation, DataContext {

    private final AnchorPane container;
    private final ImageView viewer;
    private boolean animation;

    @Getter private final ItemAlignment orientation;

    public BaseImage(Image source, ItemAlignment orientation) {
        this.container = new AnchorPane();
        this.orientation = orientation;

        this.animation = false;

        this.viewer = new ImageView(source);
    }

    @Override
    public BaseImage load() { return this; }

    public void setImage(Image image) {
        this.viewer.setImage(image);
    }

    public Image getImage() {
        return ((ImageView) this.getData()).getImage();
    }

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
    public void setAnimationTag(boolean tag) {
        this.animation = tag;
    }

    @Override
    public boolean hasTag() {
        return this.animation;
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }

    @Override
    public Object getData() {
        return this.viewer;
    }
}