package game.entity.property;

import controller.GameController;
import game.entity.Entity;
import game.property.PropertyTickable;
import game.state.GameState;
import listeners.IMoveValidity;
import util.positions.Pos;
import util.positions.Hitbox;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This class is used by {@link game.entity.Entity} to allow it to jump.
 * The jump animation consists of gradually updating the {@link Entity#getPosition()} by {@link Entity#updatePosition(Pos)}.
 * We do this gradually by making sure this class is called each 'tick', a refresh in {@link GameController#update()}, which
 * will loop over items in {@link GameState} and call upon {@link PropertyTickable#execute()}.
 * The {@link Entity} will have to add {@link PropertyTickable} by {@link Entity#getProperties()} and adding an instance.
 * Upon adding the property, a {@link Runnable} should be defined in the class itself. A listener of some sort which is called from higher layers.
 */
public class PropertyJumpState {

    /**
     * The height of the player jump
     */
    private final int jumpHeight;
    /**
     * The starting y position of the player
     */
    int playerStartY;
    int playerZ;
    private boolean isJumping;
    private long startJumpTime;
    private final int jumpDuration;
    private int jumpTime;
    private Consumer<Pos> positionUpdater;
    private Supplier<Pos> positionGetter;
    private Supplier<Hitbox> hitboxGetter;
    private final IMoveValidity moveChecker;
    private Pos startPosition;

    public PropertyJumpState(int jumpHeight, int jumpDuration, IMoveValidity validMoveChecker, Consumer<Pos> playerLocationModifier) {
        this.jumpHeight = jumpHeight;
        this.playerStartY = 0;
        this.playerZ = 0;
        this.isJumping = false;
        this.startJumpTime = 0;
        this.jumpDuration = jumpDuration;
        this.jumpTime = 0;
        this.positionUpdater = playerLocationModifier;
        this.moveChecker = validMoveChecker;
    }

    public void jump(Supplier<Pos> getPosition, Supplier<Hitbox> getHitbox) {
        if(this.isJumping)
            return;

        // Reset jump help variables
        this.playerStartY = getPosition.get().y();
        this.isJumping = true;
        this.startJumpTime = System.currentTimeMillis();
        this.jumpTime = 0;
        this.positionGetter = getPosition;
        this.hitboxGetter = getHitbox;
    }

    public void tick() {

        if(this.isJumping) {

            if(this.startJumpTime + this.jumpTime >= this.startJumpTime + jumpDuration ) {
                this.isJumping = false;
                return;
            }

            double increase = getJumpIncrease();

            if(increase < 1)
                if(jumpTime != 0 && jumpTime % getModulo() == 0)
                    increase = 1;

            // First stage
            if(jumpTime < jumpDuration/2)
                this.updatePosition(new Pos(0, -(int) increase));
            else
                this.updatePosition(new Pos(0, (int) increase));

            jumpTime++;

        }

    }

    /**
     * @return boolean determining if the player is jumping
     */
    public boolean isJumping() {
        return this.isJumping;
    }

    /**
     * @return amount of height should be gained per tick
     */
    double getJumpIncrease() {
        return (double) jumpHeight / (jumpDuration / 2);
    }

    void updatePosition(Pos addition) {
        if(moveChecker.canMoveTo(hitboxGetter.get().calculateRelativeHitbox(positionGetter.get().add(addition)))) {
            positionUpdater.accept(addition);
        } else {
            jumpTime += 2*((jumpDuration/2) - jumpTime)-1;
        }
    }

    /**
     * Sometimes the {@link PropertyJumpState#getJumpIncrease()} is < 0 at all times. Therefore, we determine a value
     * for {@link PropertyJumpState#jumpTime} such that jumpTime * jumpHeight / (jumpDuration/2) = 1
     * @return a value for jumptime for when {@link PropertyJumpState#getJumpIncrease()} reaches 1
     */
    int getModulo() {
        if(getJumpIncrease() < 0)
            return 1;
        else
            return (int) Math.ceil((double) (jumpDuration/2) / jumpHeight);
    }

}
