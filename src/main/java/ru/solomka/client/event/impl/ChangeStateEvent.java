package ru.solomka.client.event.impl;

import ru.solomka.client.core.component.item.SceneItem;
import ru.solomka.client.core.component.item.impl.button.ContextButton;
import ru.solomka.client.core.component.item.tag.Container;
import ru.solomka.client.event.Event;

public class ChangeStateEvent extends Event {

    public ChangeStateEvent() {
        super("changeState");
    }

    @Override
    public void onTriggeredEvent(Container parent, SceneItem<?> element) {
        ContextButton currentButton = (ContextButton) element;

        ContextButton activeButton = (ContextButton) parent.getSource().stream()
                .filter(e -> e instanceof ContextButton && ((ContextButton) e).getState())
                .findAny().orElse(null);

        if(currentButton.getState()) return;

        if(activeButton != null)
            activeButton.change(false);

        currentButton.change(true);
        currentButton.getItem().setStyle("-fx-background-color: rgba(36,34,69,0.35)");

        parent.getSource().stream().filter(e -> e instanceof ContextButton && !((ContextButton) e).getState()).forEach(e -> e.getItem().setStyle("-fx-background-color: transparent"));
    }
}