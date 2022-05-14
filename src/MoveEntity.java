import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class MoveEntity extends ActivityEntity{

    public MoveEntity(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    protected abstract boolean moveTo(
            WorldModel world,
            Entity target,
            EventScheduler scheduler);

    protected abstract Point nextPosition(WorldModel world, Point destPos);
}
