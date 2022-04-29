import java.util.*;

import processing.core.PImage;

public class Sapling implements EntityHealth, ActivityEntity, AnimationEntity
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int actionPeriod;
    private int animationPeriod;
    private int health;
    private int healthLimit;

    public String getId() {
        return id;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public Sapling(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod,
            int health,
            int healthLimit)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
        this.health = health;
        this.healthLimit = healthLimit;
    }

    public PImage getCurrentImage() {
        return this.images.get(this.imageIndex);
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        this.health++;
        if (!this.transformPlant(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }

    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
                scheduler.scheduleEvent(this,
                        Factory.createActivityAction(this, world, imageStore),
                        this.actionPeriod);
                scheduler.scheduleEvent(this,
                        Factory.createAnimationAction(this, 0),
                        this.getAnimationPeriod());

    }

    public PImage getCurrentImage(Entity entity) {

        return this.images.get(this.imageIndex);

    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }


    public boolean transformPlant(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        return this.transformSapling(world, scheduler, imageStore);
    }


    public boolean transformSapling(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (this.health <= 0) {
            Stump stump = Factory.createStump(this.id,
                    this.position,
                    imageStore.getImageList(Functions.STUMP_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(stump);
            //stump.scheduleActions(scheduler, world, imageStore); //not doing anything can delete

            return true;
        }
        else if (this.health >= this.healthLimit)
        {
            Tree tree = Factory.createTree("tree_" + this.id,
                    this.position,
                    Functions.getNumFromRange(Functions.TREE_ACTION_MAX, Functions.TREE_ACTION_MIN),
                    Functions.getNumFromRange(Functions.TREE_ANIMATION_MAX, Functions.TREE_ANIMATION_MIN),
                    Functions.getNumFromRange(Functions.TREE_HEALTH_MAX, Functions.TREE_HEALTH_MIN),
                    imageStore.getImageList(Functions.TREE_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
}
