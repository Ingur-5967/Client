package ru.solomka.client.core.component.layout;

import lombok.Getter;

@Getter
public abstract class AbstractLayout {

    private final String section;


    public AbstractLayout(String section) {
        this.section = section;
    }
}