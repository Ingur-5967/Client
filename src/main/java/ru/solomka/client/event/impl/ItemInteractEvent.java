package ru.solomka.client.event.impl;

import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.event.Event;

import java.io.IOException;

public class ItemInteractEvent extends Event {


    protected ItemInteractEvent() {
        super("onInteractItem");
    }

    @Override
    public void onTriggeredEvent(Container parent, SceneItem<?> element) {




    }
}
