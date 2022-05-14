import processing.core.PImage;

import java.util.List;

public abstract class ActivityEntity extends AnimationEntity{


    public ActivityEntity(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);

    }

    protected abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);



}
