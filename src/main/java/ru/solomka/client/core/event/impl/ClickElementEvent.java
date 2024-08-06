package ru.solomka.client.core.event.impl;

import ru.solomka.client.core.component.entity.SceneElement;

import java.io.IOException;

public class ClickElementEvent extends Event {

    protected ClickElementEvent(String id) {
        super(id);
    }

    @Override
    public void onTriggeredEvent(SceneElement element) {

    }
}
