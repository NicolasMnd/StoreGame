package game.entity.property;

import util.Pos;

import java.util.function.Consumer;

public class PropertyJumpState {

    /**
     * The height of the player jump
     */
    private final int jumpHeight;
    /**
     * The starting y position of the player
     */
    int playerStartY;
    private boolean isJumping;
    private long startJumpTime;
    private final int jumpDuration;
    private int jumpTime;
    private Consumer<Pos> positionUpdater;
    private Pos startPosition;

    public PropertyJumpState(int jumpHeight, int jumpDuration, Consumer<Pos> playerLocationModifier) {
        this.jumpHeight = jumpHeight;
        this.playerStartY = 0;
        this.isJumping = false;
        this.startJumpTime = 0;
        this.jumpDuration = jumpDuration;
        this.jumpTime = 0;
        this.positionUpdater = playerLocationModifier;
    }

    public void jump(Pos playerJumpPosition) {
        System.out.println("Registered jump");
        if(this.isJumping)
            return;

        System.out.println("Setting variables");
        // Reset jump help variables
        this.playerStartY = playerJumpPosition.y();
        this.isJumping = true;
        this.startJumpTime = System.currentTimeMillis();
        this.jumpTime = 0;
        this.startPosition = playerJumpPosition;
    }

    public void tick() {

        if(this.isJumping) {

            if(this.startJumpTime + this.jumpTime >= this.startJumpTime + jumpDuration) {
                this.isJumping = false;
                return;
            }

            double increase = getJumpIncrease();

            if(increase < 1)
                if(jumpTime != 0 && jumpTime % getModulo() == 0)
                    increase = 1;

            // First stage
            if(jumpTime < jumpDuration/2)
                positionUpdater.accept(new Pos(0, -(int) increase));
            else
                positionUpdater.accept(new Pos(0, (int) increase));

            jumpTime++;

        }

    }

    public boolean isJumping() {
        return this.isJumping;
    }

    double getJumpIncrease() {
        return (double) jumpHeight / (jumpDuration / 2);
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
