package ru.solomka.client.core.component.item.impl.button;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.Padding;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.impl.ImageViewer;
import ru.solomka.client.core.component.item.impl.base.BaseButton;
import ru.solomka.client.core.component.item.tag.Changed;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

import java.util.stream.IntStream;

public class ContentButton extends BaseButton implements Changed<Boolean> {

    private boolean state;

    public ContentButton(int width, int height, String id, @NotNull ImageViewer source, SceneItem<?> content, @NotNull Pair<Padding, Padding> padding) {
        super(id);

        this.state = false;

        AnchorPane parent = this.getItem();

        Padding imageEditor = padding.getFirst();
        Padding contentEditor = padding.getSecond();

        ImageView data = (ImageView) source.getData();

        if(width > 0 && height > 0)
            parent.setPrefSize(width, height);
        else {
            double fixedWidth = content != null
                    ? (data.getFitWidth() + (source.getOrientation() == ItemAlignment.LEFT ? contentEditor.getLeft() : contentEditor.getRight()) + ((AnchorPane) content.getItem()).getPrefWidth() * 3.5)
                    : data.getFitWidth();

            double fixedHeight = content != null ? IntStream.of((int) data.getFitHeight(), (int) ((AnchorPane) content.getItem()).getPrefHeight()).max().getAsInt() : data.getFitHeight();

            parent.setPrefSize(fixedWidth, fixedHeight);
        }

        if (content != null) {
            Node item = content.getItem();

            item.setLayoutX(source.getOrientation() == ItemAlignment.LEFT ? (data.getFitWidth() + contentEditor.getLeft()) : 0);
            item.setLayoutY(WindowCalcHelper.getNegativeCentre(parent, item)[1] + contentEditor.getTop() + contentEditor.getBottom());

            parent.getChildren().add(item);
        }

        int fixedX = -1;
        if (source.getOrientation() == ItemAlignment.LEFT)
            fixedX = 0;
        else if(content != null)
            fixedX = (int) (((AnchorPane) content.getItem()).getPrefWidth() + imageEditor.getRight());

        if(fixedX == -1) return;

        source.getItem().setLayoutX(fixedX + imageEditor.getLeft() + imageEditor.getRight());
        source.getItem().setLayoutY(WindowCalcHelper.getNegativeCentre(parent, source.getItem())[1] + imageEditor.getTop() + imageEditor.getBottom());

        parent.getChildren().add(source.getItem());
    }

    @Override
    public void change(Boolean newState) {
        this.state = newState;
    }

    @Override
    public Boolean getState() {
        return this.state;
    }
}