package game.entity;

import game.GameObject;
import game.entity.property.PropertyJumpState;
import game.entity.property.PropertyWalkState;
import game.property.PropertyTickable;
import game.state.GameState;
import listeners.IMoveValidity;
import util.Direction;
import util.positions.Pos;

public abstract class Entity extends GameObject {

    /**
     * Callable object defined in {@link GameState}. Used to check collision
     */
    private final IMoveValidity validMoveChecker;
    /**
     * Object that registers ticks and cares for walk state
     */
    private PropertyWalkState walkManager;
    /**
     * Objects that handles the jump animation
     */
    private PropertyJumpState jumpManager;
    private final int speed;
    private int height = 0;
    private int walkVersion = 0, idleVersion = 0;
    final int walkModulo = 10, runModulo = 8, idleModulo = 15;

    public Entity(Pos pos, int speed, IMoveValidity validMoveChecker) {
        super(pos);
        this.setFacing(Direction.RIGHT);
        this.validMoveChecker = validMoveChecker;
        this.speed = speed;
        this.walkManager = new PropertyWalkState();
        this.jumpManager = new PropertyJumpState(64, 40, validMoveChecker, (updatedJumpPos) -> this.updatePosition(getPosition().add(updatedJumpPos)));
        this.getProperties().addProperty(new PropertyTickable(this::tick));
        this.setWidth(32);
        this.setHeight(32);
    }

    /**
     * Moves the entity in a certain direction
     */
    public void move(Direction dir) {
        // Generate new position
        Pos updatedPosition = getPosition();
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
                updatedPosition = getPosition().add(new Pos(speed, -speed));
                break;
            case Direction.NORTH_WEST:
                updatedPosition = getPosition().add(new Pos(-speed, -speed));
                break;
            case Direction.SOUTH_EAST:
                updatedPosition = getPosition().add(new Pos(speed, speed));
                break;
            case Direction.SOUTH_WEST:
                updatedPosition = getPosition().add(new Pos(-speed, speed));
                break;
        }

        // Ask GameState for validity and update
        if(canMoveTo(updatedPosition)) {
            updatePosition(updatedPosition);
            this.walkManager.setWalking();
            walkVersion++;
            walkVersion %= walkModulo;
        } else System.out.println("Move not allowed!");

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
        return this.speed;
    }

    /**
     * TODO a property should handle the jumping animation
     * @return a boolean determining if the player is in a jumping state.
     */
    public boolean isJumping() {
        return this.jumpManager.isJumping();
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
