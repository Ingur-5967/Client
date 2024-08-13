package ru.solomka.client.event;

import lombok.Getter;
import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.Container;

import java.io.IOException;

@Getter
public abstract class Event {

    private final String id;

    protected Event(String id) {
        this.id = id;
    }

    public abstract void onTriggeredEvent(Container parent, SceneItem<?> element) throws IOException; //todo
}