import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Pikachu extends MoveEntity{

    public Pikachu(String id,
                   Point position,
                   List<PImage> images,
                   int actionPeriod,
                   int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }


    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> pikaTarget =
                world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(DudeNotFull.class, DudeFull.class)));

        if (pikaTarget.isPresent()) {
            Point tgtPos = pikaTarget.get().getPosition();

            if (this.moveTo(world, pikaTarget.get(), scheduler)) {
                FaintedDude fainted = Factory.createFainted("faint", tgtPos, imageStore.getImageList("faint"));
                world.addEntity(fainted);
            }
        }
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
    }

    @Override
    protected boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.getPosition().adjacent(target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        }

        else
        {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }

    }

    @Override
    protected Point nextPosition(WorldModel world, Point destPos) {

        PathingStrategy strategy = new AStarPathingStrategy();
        Predicate<Point> canPassThrough = passThrough -> world.withinBounds(passThrough) && !world.isOccupied(passThrough);
        List<Point> path = strategy.computePath(getPosition(), destPos, canPassThrough, Point::adjacent, PathingStrategy.CARDINAL_NEIGHBORS);

        if (path.size() != 0) {
            return path.get(0);
        }
        else{
            return getPosition();
        }
    }
}
