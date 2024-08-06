package ru.solomka.client.core.component.item.tag;

public interface Changed<T extends Object> {
    void change(T newState);
    T getState();
}
