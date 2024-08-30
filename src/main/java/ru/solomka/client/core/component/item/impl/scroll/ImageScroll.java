package ru.solomka.client.core.component.item.impl.scroll;

import javafx.scene.image.Image;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.item.impl.ImageViewer;
import ru.solomka.client.core.component.item.impl.button.ImageButton;
import ru.solomka.client.core.component.item.tag.enums.ItemAlignment;
import ru.solomka.client.math.WindowCalcHelper;

public class ImageScroll extends BasePage {

    public ImageScroll(int width, int height, String id) {
        super(width, height, id);
    }

    @Override
    public void init() {
        ImageButton previous = new ImageButton(23, 23, ItemAlignment.LEFT, new Image(ResourceConstant.LOGO_PREVIOUS_ELEMENT));
        ImageButton next = new ImageButton(23, 23, ItemAlignment.RIGHT, new Image(ResourceConstant.LOGO_NEXT_ELEMENT));

        previous.setLocation(5, WindowCalcHelper.getNegativeCentre(this.getItem(), previous.getItem())[1]);
        next.setLocation(this.getBounds().getWidth() - next.getItem().getPrefWidth() - 5, WindowCalcHelper.getNegativeCentre(this.getItem(), next.getItem())[1]);

        ImageViewer translation = new ImageViewer(
                (int) this.getBounds().getWidth(), (int) this.getBounds().getHeight(), ItemAlignment.NONE,
                ((ImageViewer) this.current()).getImage()
        );

        next.setup((_, _) -> translation.setImage(((ImageViewer) this.next()).getImage()));
        previous.setup((_, _) -> translation.setImage(((ImageViewer) this.previous()).getImage()));

        this.getItem().getChildren().add(translation.getItem());
        this.getItem().getChildren().add(next.getItem());
        this.getItem().getChildren().add(previous.getItem());
    }
}