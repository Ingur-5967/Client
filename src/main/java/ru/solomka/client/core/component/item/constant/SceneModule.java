package ru.solomka.client.core.component.item.constant;

public interface SceneModule<T> {
    T build() throws InstantiationException, IllegalAccessException;
}