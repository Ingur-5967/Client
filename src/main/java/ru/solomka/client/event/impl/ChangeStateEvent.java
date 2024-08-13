package ru.solomka.client.event.impl;

import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.tag.Changed;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.event.Event;

public class ChangeStateEvent extends Event {

    public ChangeStateEvent() {
        super("changeState");
    }

    @Override @SuppressWarnings("unchecked")
    public void onTriggeredEvent(Container parent, SceneItem<?> element) {
        Changed<Boolean> currentButton = (Changed<Boolean>) element;

        Changed<Boolean> activeButton = (Changed<Boolean>) parent.getSource().stream()
                .filter(e -> e instanceof Changed<?> && ((Boolean) ((Changed<?>) e).getState()))
                .findAny().orElse(null);

        if(currentButton.getState()) return;

        if(activeButton != null)
            activeButton.change(false);

        currentButton.change(true);

        if(!element.getItem().getId().equals("profileButton"))
            element.getItem().setStyle("-fx-background-color: rgba(36,34,69,0.35)");

        parent.getSource().stream().filter(e -> e instanceof Changed<?> && !((boolean) ((Changed<?>) e).getState())).forEach(e -> e.getItem().setStyle("-fx-background-color: transparent"));
    }
}