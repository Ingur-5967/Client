package ru.solomka.client.core.component.option;

public enum CssProperties {

    TEXT_FILL_COLOR("-fx-text-fill: %s"),
    BACKGROUND_COLOR("-fx-background-color: %s"),
    BORDER_COLOR("-fx-border-color: %s"),
    FONT_SIZE("-fx-font-size: %s"),
    OPACITY("-fx-opacity: %s"),
    BORDER_RADIUS("-fx-border-radius: %s"),
    FONT_FAMILY("-fx-font-family: %s"),
    OTHER("%s");

    private final String property;
    CssProperties(String property) {
        this.property = property;
    }

    public String getProperty(Object value) {
        return String.format(property, value);
    }
}