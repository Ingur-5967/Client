package ru.solomka.client.core.component.item.impl.text;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.item.BaseComponent;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.DataContext;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;
import ru.solomka.client.tool.Pair;

public class InputField implements BaseComponent<InputField, AnchorPane>, DataContext {

    private final AnchorPane container;
    private final TextField area;

    public InputField(int width, int height, @NotNull Pair<String, Integer> source, CssContext ...properties) {
        this.container = new AnchorPane();

        double fixedWidth = width + ((double) (source.getFirst().length() * source.getSecond())/3);

        this.container.setPrefSize(fixedWidth, height);
        this.container.setStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("transparent")).getCss());

        this.area = new TextField();
        this.area.setPrefSize(fixedWidth, height);

        this.area.setStyle(CssContext.build(properties));
        this.area.setPromptText(source.getFirst());
        this.area.setFont(Font.font(source.getSecond()));

        this.container.getChildren().add(this.area);
    }

    @Override
    public InputField load() { return this; }

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
        return this.area.getText();
    }
}