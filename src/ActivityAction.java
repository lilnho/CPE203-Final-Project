public final class ActivityAction implements Action
{
    private ActivityEntity entity;
    private WorldModel world;
    private ImageStore imageStore;
    private int repeatCount;

    public ActivityAction(
            ActivityEntity entity,
            WorldModel world,
            ImageStore imageStore,
            int repeatCount)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler) {
        this.entity.executeActivity(this.world, this.imageStore, scheduler);
    }

}
