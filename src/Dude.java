import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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

    protected Point nextPosition(
            WorldModel world, Point destPos) {
        PathingStrategy strategy = new AStarPathingStrategy();
        Predicate<Point> canPassThrough = passThrough -> world.withinBounds(passThrough) && (!world.isOccupied(passThrough) || (world.getOccupancyCell(passThrough).getClass() == Stump.class));
        List<Point> path = strategy.computePath(getPosition(), destPos, canPassThrough, Point::adjacent, PathingStrategy.CARDINAL_NEIGHBORS);

        if (path.size() != 0) {
            return path.get(0);
        } else {
            return getPosition();
        }
    }

}


