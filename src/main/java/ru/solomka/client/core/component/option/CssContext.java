package ru.solomka.client.core.component.option;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CssContext {
    String css;

    public static CssContext empty() {
        return new CssContext("");
    }
}