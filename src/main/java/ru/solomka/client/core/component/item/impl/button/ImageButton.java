package ru.solomka.client.core.component.item.impl.button;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.solomka.client.AppLoader;
import ru.solomka.client.core.component.item.impl.ImageViewer;
import ru.solomka.client.core.component.item.tag.Interact;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.tool.Pair;

import java.util.function.BiConsumer;

public class ImageButton extends ImageViewer implements Interact {

    public ImageButton(int width, int height, ItemAlignment orientation, Image source) {
        super(width, height, orientation, source);
    }

    @Override
    public <E extends Event> void setup(BiConsumer<E, Pair<Stage, Node>> interact) {
        this.getItem().setOnMouseClicked(e -> interact.accept((E) e, new Pair<>(AppLoader.getPrimaryStage(), this.getItem())));
    }
}