package ru.solomka.client.core.event.impl;

import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.entity.SceneElement;

import java.io.IOException;

public class ChangeStageEvent extends Event {

    public ChangeStageEvent() {
        super("ChangeStageEvent");
    }

    @Override //TODO: переделать залупу
    public void onTriggeredEvent(@NotNull SceneElement element) {
    }
}