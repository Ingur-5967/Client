package ru.solomka.client.core.component.item.impl.text;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.item.impl.base.BaseText;
import ru.solomka.client.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

public class DefaultLabel extends BaseText {

    public DefaultLabel(String content, @NotNull Pair<Integer, String> setting) {
        super(content, setting.getFirst());

        AnchorPane parent = this.getItem();

        Label text = (Label) this.getData();
        text.setStyle(setting.getSecond());

        parent.setPrefSize((int) (text.getFont().getSize() + text.getText().length()), setting.getFirst());

        text.setLayoutX(0);
        text.setLayoutY(WindowCalcHelper.getPositiveCentreOfLabel(parent, text)[1]);

        parent.getChildren().add(text);
    }
}