package ru.solomka.client.core.component.item.tag;

import javafx.animation.Transition;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public interface ItemAnimation {

    void setAnimationTag(boolean tag);
    boolean hasTag();

    default void animation(@NotNull Supplier<? extends Transition> properties) throws InstantiationException, IllegalAccessException {
        if(this.hasTag()) return;

        Transition animation = properties.get();
        animation.setOnFinished(_ -> this.setAnimationTag(false));

        animation.play();

        this.setAnimationTag(true);
    }
}