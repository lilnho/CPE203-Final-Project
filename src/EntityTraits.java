import processing.core.PImage;
import java.util.List;

public abstract class EntityTraits implements Entity{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    public EntityTraits(String id, Point position, List<PImage> images){
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public PImage getCurrentImage() {
        return this.images.get(this.imageIndex);
    }

    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public List<PImage> getImages() {
        return images;
    }
}
