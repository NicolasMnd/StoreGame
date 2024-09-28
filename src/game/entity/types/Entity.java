package game.entity.types;

import game.GameObject;
import game.entity.util.LimbTracker;
import game.entity.property.PropertyJumpState;
import game.entity.property.PropertyWalkState;
import game.inventory.Inventory;
import game.property.PropertyTickable;
import game.state.GameState;
import listeners.IAnimationListener;
import listeners.IMoveValidity;
import util.Direction;
import util.positions.Pos;

public abstract class Entity extends GameObject {

    /**
     * Callable object defined in {@link GameState}. Used to check collision
     */
    private final IMoveValidity validMoveChecker;
    private final IAnimationListener animationListener;
    /**
     * Object that registers ticks and cares for walk state
     */
    private PropertyWalkState walkManager;
    /**
     * Objects that handles the jump animation
     */
    private PropertyJumpState jumpManager;
    private final int speed;
    private int walkVersion = 0, idleVersion = 0;
    final int walkModulo = 10;
    private boolean ghosting = false, sprinting = false;
    private final Inventory inventory;

    public Entity(Pos pos, int speed, IMoveValidity validMoveChecker, IAnimationListener animationListener) {
        super(pos);
        this.setFacing(Direction.RIGHT);
        this.validMoveChecker = validMoveChecker;
        this.animationListener = animationListener;
        this.speed = speed;
        this.walkManager = new PropertyWalkState();
        this.jumpManager = new PropertyJumpState(64, 16, validMoveChecker, (updatedJumpPos) -> this.updatePosition(getPosition().add(updatedJumpPos)));
        this.inventory = new Inventory();
        this.getProperties().addProperty(new PropertyTickable(this::tick));
        this.setWidth(32);
        this.setHeight(32);
    }

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Moves the entity in a certain direction
     */
    public void move(Direction dir) {
        // Generate new position
        Pos updatedPosition = getPosition();
        int speed = getSpeed();

        switch(dir) {
            case Direction.UP:
                this.setFacing(Direction.UP);
                updatedPosition = getPosition().add(new Pos(0, -speed));
                break;
            case Direction.DOWN:
                this.setFacing(Direction.DOWN);
                updatedPosition = getPosition().add(new Pos(0, speed));
                break;
            case Direction.RIGHT:
                this.setFacing(Direction.RIGHT);
                updatedPosition = getPosition().add(new Pos(speed, 0));
                break;
            case Direction.LEFT:
                this.setFacing(Direction.LEFT);
                updatedPosition = getPosition().add(new Pos(-speed, 0));
                break;
            case Direction.NORTH_EAST:
                updatedPosition = getPosition().add(new Pos((int) (speed*Math.sqrt(0.5)), (int) (-speed*Math.sqrt(0.5))));
                break;
            case Direction.NORTH_WEST:
                updatedPosition = getPosition().add(new Pos((int) (-speed*Math.sqrt(0.5)), (int) (-speed*Math.sqrt(0.5))));
                break;
            case Direction.SOUTH_EAST:
                updatedPosition = getPosition().add(new Pos((int) (speed*Math.sqrt(0.5)), (int) (speed*Math.sqrt(0.5))));
                break;
            case Direction.SOUTH_WEST:
                updatedPosition = getPosition().add(new Pos((int) (-speed*Math.sqrt(0.5)), (int) (speed*Math.sqrt(0.5))));
                break;
        }

        // Ask GameState for validity and update
        if(canMoveTo(updatedPosition) || ghosting) {
            updatePosition(updatedPosition);
            this.walkManager.setWalking();
            walkVersion++;
            walkVersion %= walkModulo;
        }

    }

    /**
     * Used the {@link Entity#validMoveChecker} to ask {@link GameState} if the {@link Pos} is a valid location
     * @param position the destination {@link Pos}
     * @return a boolean determining the validity of the position
     */
    protected boolean canMoveTo(Pos position) {
        return validMoveChecker.canMoveTo(this.getHitbox().calculateRelativeHitbox(position));
    }

    /**
     * Makes this entity jump
     */
    public void jump() {
        if(jumpManager.isJumping())
            return;
        jumpManager.jump(this::getPosition, this::getHitbox);
    }

    /**
     * @return the walk version integer
     */
    public int getWalkVersion() {
        return this.walkVersion < walkModulo/2 ? 1 : 2;
    }

    /**
     * @return the idle version integer
     */
    public int getIdleVersion() {
        return this.idleVersion;
    }

    /**
     * TODO a property should create a timer of 2 seconds which sets a boolean in this class to false after walking
     * @return a boolean determining if the player is in a walking state.
     */
    public boolean isWalking() {
        return !this.walkManager.isIdling();
    }

    /**
     * @return the speed of the player
     */
    public int getSpeed() {
        return this.speed + (isSprinting() ? speed : 0);
    }

    /**
     * @return a boolean determining if the player is in a jumping state.
     */
    public boolean isJumping() {
        return this.jumpManager.isJumping();
    }

    public abstract LimbTracker getLimbTracker();

    public void setGhosting(boolean ghosting) {
        this.ghosting = ghosting;
    }

    public boolean isGhosting() {
        return this.ghosting;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public boolean isSprinting() {
        return this.sprinting;
    }

    private void tick() {
        walkManager.tick();
        jumpManager.tick();
        this.updateListener();
    }

    /**
     * Children class access to use {@link PropertyTickable}
     */
    protected void updateListener() {

    }

}
