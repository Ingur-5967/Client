package ru.solomka.client.core.component.entity;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.tool.Resource;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

@Data @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
public class SceneEntry {

    final String linkedFXML;

    public Scene initScene(int weight, int height) {
        try {
            return new Scene(getFXMLLoader().load(), weight, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SceneElement findModifiableElement(@NotNull Pane pane, Predicate<Node> filter) {
        return castToElement(Objects.requireNonNull(pane.getChildren().stream().filter(filter).findAny().orElse(null)));
    }

    public SceneElement findUnmodifiableElement(@NotNull Scene parent, Predicate<Node> filter) {
        return castToElement(Objects.requireNonNull(parent.getRoot().getChildrenUnmodifiable().stream().filter(filter).findAny().orElse(null)));
    }

    public FXMLLoader getFXMLLoader() {
        return new FXMLLoader(Resource.getFileAsResource(linkedFXML));
    }

    public SceneElement castToElement(Node node) {
        return new SceneElement((node.getId() == null ? "" : node.getId()), () -> node, (Pane) node.getParent());
    }
}