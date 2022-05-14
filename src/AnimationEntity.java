import processing.core.PImage;

import java.util.List;

public abstract class AnimationEntity extends EntityTraits{

    private int animationPeriod;
    private int actionPeriod;

    public AnimationEntity(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images);
        this.animationPeriod = animationPeriod;
        this.actionPeriod = actionPeriod;
    }

    public int getActionPeriod() {
        return actionPeriod;
    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }

    public void nextImage() {
        this.setImageIndex((this.getImageIndex() + 1) % this.getImages().size());
    }


    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {

        scheduler.scheduleEvent(this,
                Factory.createAnimationAction(this, 0),
                this.getAnimationPeriod());
        if (this instanceof ActivityEntity) {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction((ActivityEntity) this, world, imageStore),
                    this.actionPeriod);
        }

    }

}
