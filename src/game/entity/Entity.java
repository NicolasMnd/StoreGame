package game.entity;

import game.GameObject;
import listeners.IMoveValidity;
import util.Direction;
import util.Pos;

public abstract class Entity extends GameObject {

    private final IMoveValidity validMoveChecker;
    private boolean isJumping = false;
    private final int speed;
    private int walkVersion = 0, idleVersion = 0;
    final int walkModulo = 15;
    final int runModulo = 10;
    final int idleModulo = 25;

    public Entity(Pos pos, int speed, IMoveValidity validMoveChecker) {
        super(pos);
        this.validMoveChecker = validMoveChecker;
        this.speed = speed;
    }

    /**
     * Moves the entity in a certain direction
     */
    public void move(Direction dir) {
        System.out.println("Requested to move in " + dir.name() + " from " + getPosition().getFormat());
        // Generate new position
        Pos updatedPosition = getPosition();
        switch(dir) {
            case Direction.UP -> updatedPosition = getPosition().add(new Pos(0, -speed));
            case Direction.DOWN -> updatedPosition = getPosition().add(new Pos(0, speed));
            case Direction.RIGHT -> updatedPosition = getPosition().add(new Pos(speed, 0));
            case Direction.LEFT -> updatedPosition = getPosition().add(new Pos(-speed, 0));
        }
        System.out.println("updated: " + updatedPosition.getFormat());

        // Ask GameState for validity and update
        if(canMoveTo(updatedPosition)) {
            System.out.println("The position is valid");
            updatePosition(updatedPosition);
        }
        else System.out.println("The position is invalid");

        System.out.println("New Position = " + getPosition().getFormat());

    }

    /**
     * Used the {@link Entity#validMoveChecker} to ask {@link game.GameState} if the {@link Pos} is a valid location
     * @param position the destination {@link Pos}
     * @return a boolean determining the validity of the position
     */
    protected boolean canMoveTo(Pos position) {
        return validMoveChecker.canMoveTo(position);
    }

    /**
     * Makes this entity jump
     */
    public void jump() {
        if(isJumping)
            return;
        isJumping = true;
    }

    /**
     * @return the walk version integer
     */
    public int getWalkVersion() {
        return this.walkVersion;
    }

    /**
     * @return the idle version integer
     */
    public int getIdleVersion() {
        return this.idleVersion;
    }

}
