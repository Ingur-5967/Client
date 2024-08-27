package ru.solomka.client.core.component.entity;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.file.utils.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Data @FieldDefaults(level = AccessLevel.PRIVATE)
public class SceneEntry {

    final String linkedFXML;
    @Setter List<SceneItem<?>> source;

    public SceneEntry(String fxml) {
        this.linkedFXML = fxml;
        this.source = new ArrayList<>();
    }

    public Scene initScene(int width, int height) {
        try {
            return new Scene(getFXMLLoader().load(), width, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SceneItem<?> findElement(@NotNull Container container, Predicate<SceneItem<?>> filter) {
        return container.getSource().stream().filter(filter).findAny().orElse(null);
    }

    public FXMLLoader getFXMLLoader() {
        return new FXMLLoader(Resource.getFileAsResource(linkedFXML));
    }
}