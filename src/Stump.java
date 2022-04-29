import java.util.*;

import processing.core.PImage;


public class Stump implements Entity
{
    private String id;
    private Point position;

    public void setPosition(Point position) {
        this.position = position;
    }

    private List<PImage> images;
    private int imageIndex;


    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public Stump(
            String id,
            Point position,
            List<PImage> images)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }

    public PImage getCurrentImage() {
        return this.images.get(this.imageIndex);
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

}
