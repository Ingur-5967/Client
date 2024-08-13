package ru.solomka.client.core.component;

import lombok.Getter;
import ru.solomka.client.file.utils.Resource;

public class ResourceConstant {

    public static final int DEFAULT_SPACE_ELEMENT_BAR = 10;
    public static final int DEFAULT_TEXT_SIZE_BAR_TITLE = 16;
    public static final int DEFAULT_TEXT_SIZE_BAR_SUBTITLE = 12;
    public static final int DEFAULT_SIZE_ICON_BAR = 33;
    public static final int DEFAULT_SIZE_BUTTON_CONTAINER = 38;
    public static final int DEFAULT_SIZE_LOGO = 33;

    public static final int DEFAULT_VERTICAL_CONTENT_STEP = 9;
    public static final int DEFAULT_HORIZONTAL_CONTENT_STEP = 4;

    public static final String LOGO_CLOSE_APP = Resource.getFileAsResource("icons/close-app-icon.png").getPath().substring(1);
    public static final String LOGO_WINDOWED_APP = Resource.getFileAsResource("icons/windowed-app-icon.png").getPath().substring(1);
    public static final String LOGO_ROLL_UP_APP = Resource.getFileAsResource("icons/roll-up-app-icon.png").getPath().substring(1);
    public static final String LOGO_ICON = Resource.getFileAsResource("icons/application-icon.png").getPath().substring(1);
    public static final String LOGO_BAR_PRODUCT = Resource.getFileAsResource("icons/bar-icons/product-icon.png").getPath().substring(1);
    public static final String LOGO_BAR_SETTING = Resource.getFileAsResource("icons/bar-icons/fixed-setting-icon.png").getPath().substring(1);
    public static final String LOGO_BAR_HOME = Resource.getFileAsResource("icons/bar-icons/fixed-home-icon.png").getPath().substring(1);

    @Getter
    public enum Color {

        BACKGROUND_BAR("#312c4d"),

        MAIN_TEXT_BAR("white"),
        SUB_TEXT_BAR("rgba(232,232,232,0.43)"),

        ON_FOCUS_ELEMENT("rgba(207,206,206,0.14)"),

        INVISIBLE("transparent");

        private final String color;

        Color(String color) {
            this.color = color;
        }
    }

}