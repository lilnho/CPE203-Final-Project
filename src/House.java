import java.util.*;

import processing.core.PImage;

public class House implements Entity
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;


    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }



    public House(
            String id,
            Point position,
            List<PImage> images)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }


    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public PImage getCurrentImage() {

        return this.images.get(this.imageIndex);

    }

}
