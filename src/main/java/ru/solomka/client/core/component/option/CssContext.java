package ru.solomka.client.core.component.option;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CssContext {
    String css;

    public static String build(CssContext ...contexts) {
        return String.join(";", Arrays.stream(contexts).map(CssContext::getCss).toList());
    }

    public static CssContext empty() {
        return new CssContext("");
    }
}