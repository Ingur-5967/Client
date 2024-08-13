package ru.solomka.client.core.component.item.impl.button;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.impl.DefaultLabel;
import ru.solomka.client.core.component.item.tag.Changed;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.math.WindowCalcHelper;

public class ModuleButton implements Changed<Boolean>, BaseComponent<ModuleButton, AnchorPane> {

    private final AnchorPane container;
    private boolean state;

    public ModuleButton(int weight, int height, int space, String id, @NotNull SceneItem<?> sceneItem, Image icon, CssContext ...properties) {

        this.container = new AnchorPane();

        Node instanceItem = sceneItem.getItem();

        this.container.setPrefSize(weight + (double) space/2, height);
        this.container.setStyle(CssContext.build(properties));
        this.container.setId(id);

        ImageView innerIcon = new ImageView(icon);

        innerIcon.setFitWidth(ResourceConstant.DEFAULT_SIZE_ICON_BAR - 2);
        innerIcon.setFitHeight(ResourceConstant.DEFAULT_SIZE_ICON_BAR - 2);

        innerIcon.setLayoutX(innerIcon.getLayoutX() + (double) space/2);
        innerIcon.setLayoutY(WindowCalcHelper.getNegativeCentre(container, innerIcon)[1]);

        instanceItem.setLayoutX(innerIcon.getFitWidth() + space);

        instanceItem.setLayoutY(
                (sceneItem instanceof DefaultLabel) ?
                WindowCalcHelper.getNegativeCentreOfLabel(container, (Label) instanceItem)[1] :
                WindowCalcHelper.getNegativeCentre(container, instanceItem)[1] - 9
        );

        this.container.getChildren().addAll(innerIcon, instanceItem);
    }


    @Override
    public ModuleButton load() { return this; }

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
    public void change(Boolean newState) {
        this.state = newState;
    }

    @Override
    public Boolean getState() {
        return this.state;
    }
}
