package ru.solomka.client.event.impl;

import ru.solomka.client.core.component.entity.SceneElement;

public class ClickElementEvent extends Event {

    protected ClickElementEvent(String id) {
        super(id);
    }

    @Override
    public void onTriggeredEvent(SceneElement element) {

    }
}
