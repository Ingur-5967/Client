package ru.solomka.client.core.component;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.option.CssContext;

import java.io.File;
import java.util.Arrays;

public class ComponentBuilder<T extends Node> {

    private final T source;

    private ComponentBuilder(T source) {
        this.source = source;
    }

    public ComponentBuilder<T> css(CssContext ...css) {
        source.setStyle(CssContext.build(css));
        return this;
    }

    public ComponentBuilder<T> text(String content) {
        if(!(source instanceof Label))
            throw new IllegalArgumentException("Component must have a label");

        ((Label) source).setText(content);

        return this;
    }

    public ImageWrapper wrapper() {

        if(!(source instanceof ImageView))
            throw new IllegalArgumentException("Component must have a image");

//        ImageView logo = new ImageView(new File("D:\\Profile-test.png").getPath());
//
//        logo.setFitHeight(30);
//        logo.setFitWidth(30);
//
//
//        // set a clip to apply rounded border to the original image.
//        Rectangle clip = new Rectangle(
//                logo.getFitWidth(), logo.getFitHeight()
//        );
//        clip.setArcWidth(30);
//        clip.setArcHeight(30);
//        logo.setClip(clip);
//
//        // snapshot the rounded image.
//        SnapshotParameters parameters = new SnapshotParameters();
//        parameters.setFill(Color.TRANSPARENT);
//        WritableImage image = logo.snapshot(parameters, null);
//
//        // remove the rounding clip so that our effect can show through.
//        logo.setClip(null);
//
//        // apply a shadow effect.
//        logo.setEffect(new DropShadow(5, Color.BLACK));
//
//        // store the rounded image in the imageView.
//        logo.setImage(image);
        return new ImageWrapper(((ImageView) source).getImage());
    }

    public static class ImageWrapper {

        private final ImageView view;

        protected ImageWrapper(Image view) {
            this.view = new ImageView(view);
        }

        public ImageWrapper size(int width, int height) {
            view.setFitWidth(width);
            view.setFitHeight(height);
            return this;
        }

        public ImageWrapper clip(int width, int height) {
            Rectangle clip = new Rectangle(view.getFitWidth(), view.getFitHeight());

            clip.setArcWidth(width);
            clip.setArcHeight(height);

            view.setClip(clip);

            return this;
        }

        public ImageWrapper snapshot() {
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = view.snapshot(parameters, null);

            view.setEffect(new DropShadow(5, Color.BLACK));
            view.setImage(image);

            return this;
        }

        public ImageView get() {
            return this.view;
        }
    }

    public T create() {
        return source;
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static <S extends Node> ComponentBuilder<S> of(S source) {
        return new ComponentBuilder<>(source);
    }

}