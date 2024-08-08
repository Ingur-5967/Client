package ru.solomka.client.core.component.item.tag;

public interface Changed<T> {
    void change(T newState);
    T getState();
}
