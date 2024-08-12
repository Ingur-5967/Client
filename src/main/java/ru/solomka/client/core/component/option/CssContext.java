package ru.solomka.client.core.component.option;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CssContext {
    String css;

    @NotNull
    @Contract("_ -> new")
    public static String build(CssContext ...contexts) {
        return String.join(";", Arrays.stream(contexts).map(CssContext::getCss).toList());
    }

    @NotNull
    @Contract("_ -> new")
    public static CssContext from(String css) {
        return new CssContext(css);
    }

    @NotNull
    @Contract(" -> new")
    public static CssContext empty() {
        return new CssContext("");
    }
}