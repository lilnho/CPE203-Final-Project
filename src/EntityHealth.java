import processing.core.PImage;

import java.util.List;

public abstract class EntityHealth extends ActivityEntity{

    private int health;
    private int healthLimit;
    public EntityHealth(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
        this.healthLimit = healthLimit;
    }

    public int getHealthLimit() { return healthLimit; }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
