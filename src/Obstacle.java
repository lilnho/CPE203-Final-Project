import java.util.*;

import processing.core.PImage;

public class Obstacle extends AnimationEntity
{
    public Obstacle(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod, 0);
    }


}
