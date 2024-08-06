package ru.solomka.client.core.component.item.impl;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.ComponentBuilder;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MultiLabel implements BaseComponent<MultiLabel, AnchorPane> {

    private final AnchorPane container;

    public MultiLabel(@NotNull Pair<ComponentBuilder<Label>, ComponentBuilder<Label>> innerContent, int verticalSpace, CssContext... css) {

        this.container = new AnchorPane();
        this.container.setStyle(String.join(";", Arrays.stream(css).map(CssContext::getCss).toList()));

        Label mainContent = innerContent.getFirst().create();
        Label subContent = innerContent.getSecond().create();

        int length = Stream.of(mainContent, subContent).mapToInt(c -> c.getText().length()).sum();
        int maxWeight = Stream.of(mainContent, subContent).mapToInt(c -> (int) (c.getFont().getSize() + c.getText().length())).max().getAsInt();
        this.container.setPrefSize((mainContent.getFont().getSize() + subContent.getFont().getSize())/2 + length, maxWeight);

        List<Node> remaining = Arrays.asList(mainContent, subContent);

        for (int index = 0; index < remaining.size(); index++) {
            double[] centre = WindowCalcHelper.getNegativeCentreOfLabel(container, (Label) remaining.get(index));

            if(index == 0)
                remaining.get(index).setLayoutY(centre[1]);
            else remaining.get(index).setLayoutY(centre[1] + verticalSpace);

            remaining.get(index).setLayoutX(0);
        }

        this.container.setPrefSize(maxWeight, (mainContent.getFont().getSize() + subContent.getFont().getSize()));
        this.container.getChildren().addAll(remaining);
    }

    @Override
    public MultiLabel load() {
        return this;
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
}