import java.util.*;

import processing.core.PImage;

public class Obstacle implements AnimationEntity, ActivityEntity
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int animationPeriod;



    public String getId() {
        return id;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public Obstacle(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.animationPeriod = animationPeriod;
    }

    public PImage getCurrentImage() {
        return this.images.get(this.imageIndex);
    }

    public List<PImage> getImages() {
        return images;
    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
                scheduler.scheduleEvent(this,
                        Factory.createAnimationAction(this, 0),
                        this.getAnimationPeriod());
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }
}
