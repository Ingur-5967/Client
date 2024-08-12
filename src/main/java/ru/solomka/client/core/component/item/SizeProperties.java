package ru.solomka.client.core.component.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data @AllArgsConstructor @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SizeProperties {
    double width;
    double height;

    double[] diagonal = new double[]{0,0}; // todo
}
