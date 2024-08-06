package ru.solomka.client.core.component.item.impl.button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.item.tag.Changed;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.math.WindowCalcHelper;
import ru.solomka.client.tool.Pair;

import java.util.Arrays;

/**
 * Class of button with image and content
 **/

public class ContextButton implements BaseComponent<ContextButton, AnchorPane>, Changed<Boolean> {

    private final AnchorPane container;
    private boolean active;

    public ContextButton(int weight, int height, int space, Pair<String, CssContext[]> content, Image icon, CssContext ...containerCss) {
        this.active = false;

        this.container = new AnchorPane();
        this.container.setPrefSize((weight + content.getSecond().length * 1.2) + (double) space/2, height);
        this.container.setStyle(String.join(";", Arrays.stream(containerCss).map(CssContext::getCss).toList()));

        Label innerContent = new Label(content.getFirst());
        innerContent.setStyle(String.join(";", Arrays.stream(content.getSecond()).map(CssContext::getCss).toList()));

        ImageView innerIcon = new ImageView(icon);
        innerIcon.setFitWidth(ResourceConstant.DEFAULT_SIZE_ICON_BAR);
        innerIcon.setFitHeight(ResourceConstant.DEFAULT_SIZE_ICON_BAR);

        innerIcon.setLayoutX(innerIcon.getLayoutX() + (double) space/2);
        innerIcon.setLayoutY(WindowCalcHelper.getNegativeCentre(container, innerIcon)[1]);

        innerContent.setLayoutX(innerIcon.getFitWidth() + space);
        innerContent.setLayoutY(WindowCalcHelper.getNegativeCentreOfLabel(container, innerContent)[1]);

        if(this.container.getPrefWidth() - innerContent.getLayoutX() < 5)
            this.container.setPrefWidth(this.container.getPrefWidth() + innerContent.getFont().getSize());

        this.container.getChildren().addAll(innerContent, innerIcon);
    }

    @Override
    public ContextButton load() {
        return this;
    }

    @Override
    public void setLocation(double x, double y) {
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
    }


    @Override
    public void change(Boolean newState) {
        this.active = newState;
    }

    @Override
    public Boolean getState() {
        return this.active;
    }

    @Override
    public AnchorPane getItem() {
        return this.container;
    }
}