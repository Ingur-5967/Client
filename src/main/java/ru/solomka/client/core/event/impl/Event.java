package ru.solomka.client.core.event.impl;

import lombok.Getter;
import ru.solomka.client.core.component.entity.SceneElement;

import java.io.IOException;

@Getter
public abstract class Event {

    private final String id;

    protected Event(String id) {
        this.id = id;
    }

    public abstract void onTriggeredEvent(SceneElement element) throws IOException; //todo
}