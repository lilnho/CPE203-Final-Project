import java.util.List;

import processing.core.PImage;

/**
 * Represents a background for the 2D world.
 */
public final class Background
{
    private String id;

    public List<PImage> getImages() {
        return images;
    }

    private List<PImage> images;

    public int getImageIndex() {
        return imageIndex;
    }

    private int imageIndex;

    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }
}
