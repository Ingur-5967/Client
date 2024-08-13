package ru.solomka.client.core.component.option;

public enum CssProperties {

    TEXT_FILL_COLOR("-fx-text-fill: %s"),
    PROMPT_TEXT_FILL_COLOR("-fx-prompt-text-fill: %s"),
    BACKGROUND_COLOR("-fx-background-color: %s"),
    TEXT_BOX_BORDER("-fx-text-box-border: %s"),
    BORDER_COLOR("-fx-border-color: %s"),
    FONT_SIZE("-fx-font-size: %s"),
    OPACITY("-fx-opacity: %s"),
    FOCUS_COLOR("-fx-focus-color: %s"),
    PADDING("-fx-padding: %s"),
    BORDER_RADIUS("-fx-border-radius: %s"),
    FONT_FAMILY("-fx-font-family: %s"),
    BACKGROUND_RADIUS("-fx-background-radius: %s"),
    OTHER("%s");

    private final String property;
    CssProperties(String property) {
        this.property = property;
    }

    public String getProperty(Object value) {
        return String.format(property, value);
    }
}