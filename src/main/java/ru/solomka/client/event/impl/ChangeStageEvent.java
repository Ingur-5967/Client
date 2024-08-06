package ru.solomka.client.event.impl;

import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.entity.SceneElement;

public class ChangeStageEvent extends Event {

    public ChangeStageEvent() {
        super("ChangeStageEvent");
    }

    @Override //todo
    public void onTriggeredEvent(@NotNull SceneElement element) {
    }
}