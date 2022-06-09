import processing.core.PImage;

import java.util.List;

public class Factory {

    public static Action createAnimationAction(AnimationEntity entity, int repeatCount) {
        return new AnimationAction(entity,
                repeatCount);
    }

    public static Action createActivityAction(
            ActivityEntity entity, WorldModel world, ImageStore imageStore)
    {
        return new ActivityAction(entity, world, imageStore, 0);
    }

    public static House createHouse(
            String id, Point position, List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Obstacle createObstacle(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Obstacle(id, position, images,
                animationPeriod);
    }

    public static Tree createTree(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new Tree(id, position, images,
                actionPeriod, animationPeriod, health, 0);
    }

    public static Stump createStump(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Stump(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static Sapling createSapling(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Sapling(id, position, images,
                WorldLoader.SAPLING_ACTION_ANIMATION_PERIOD, WorldLoader.SAPLING_ACTION_ANIMATION_PERIOD, 0, WorldLoader.SAPLING_HEALTH_LIMIT);
    }

    public static Fairy createFairy(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Fairy(id, position, images,
                actionPeriod, animationPeriod);
    }

    // need resource count, though it always starts at 0
    public static DudeNotFull createDudeNotFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images)
    {
        return new DudeNotFull(id, position, images, resourceLimit, 0,
                actionPeriod, animationPeriod);
    }

    // don't technically need resource count ... full
    public static DudeFull createDudeFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images) {
        return new DudeFull(id, position, images, resourceLimit, 0,
                actionPeriod, animationPeriod);
    }

    public static Pikachu createPikachu(String id,
                                        Point position,
                                        List<PImage> images,
                                        int actionPeriod,
                                        int animationPeriod)
    {
        return new Pikachu(id, position, images, actionPeriod, animationPeriod);
    }

    public static FaintedDude createFainted(String id,
                                            Point position,
                                            List<PImage> images)
    {
        return new FaintedDude(id, position, images);
    }


}
