package game.entity.property;

/**
 * Class that helps a {@link game.entity.Entity} object to go from walking state to idling state in a timely fashion
 * This is used in {@link game.entity.Entity} and ticked by a {@link game.property.PropertyTickable} class.
 */
public class PropertyWalkState {

    private final int walkToIdleDelay = 1000;
    private long lastWalkTime;
    private boolean idle = true;

    public PropertyWalkState() {
        lastWalkTime = System.currentTimeMillis();
    }

    public void setWalking() {
        this.lastWalkTime = System.currentTimeMillis();
        this.idle = false;
    }

    /**
     * Ticks the class to check if move state should be walking or idling.
     */
    public void tick() {
        if(this.lastWalkTime + this.walkToIdleDelay < System.currentTimeMillis())
            this.idle = true;
    }

    /**
     * @return boolean determining if the object is idling after {@link PropertyWalkState#walkToIdleDelay}ms
     */
    public boolean isIdling() {
        return this.idle;
    }

}
