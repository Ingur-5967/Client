package ru.solomka.client.core.component;

import javafx.scene.layout.Pane;

public interface Component {
    void setLocation(int x, int y);
    <P extends Pane> P getComponentParent();
    void setLocationLayout(int x, int y);
    void setSize(double width, double height);
    double[] getSize();
}
