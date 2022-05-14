import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Dude extends MoveEntity {

    private int resourceLimit;
    private int resourceCount;

    public Dude(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int resourceLimit, int resourceCount) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
    }

    public int getResourceLimit(){return resourceLimit;}

    public void setResourceLimit(int resourceLimit) {
        this.resourceLimit = resourceLimit;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    public int getResourceCount() {return resourceCount;}

    protected abstract boolean moveTo(
            WorldModel world,
            Entity target,
            EventScheduler scheduler);

    protected abstract Point nextPosition(WorldModel world, Point destPos);



}


