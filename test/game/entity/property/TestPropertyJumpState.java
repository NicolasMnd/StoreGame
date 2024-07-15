package game.entity.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Pos;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestPropertyJumpState {

    PropertyJumpState jumpHelper;
    Pos pos;
    int jumpHeight, jumpDuration;

    @BeforeEach
    public void init() {
        jumpHeight = 20;
        jumpDuration = 40;
        this.jumpHelper = new PropertyJumpState(jumpHeight, jumpDuration, updatePositionListener());
        this.pos = new Pos(0,0);
    }

    @Test
    public void testJump_StartY() {
        this.jumpHelper.jump(pos);
        assertEquals(jumpHelper.playerStartY, pos.y());
    }

    @Test
    public void testJumpIncrease() {
        assertEquals(jumpHelper.getJumpIncrease(), (jumpHeight/(jumpDuration/2)));
        tick(this.jumpHelper, 2);
        assertEquals(jumpHelper.getJumpIncrease(), (jumpHeight/(jumpDuration/2)));
        tick(this.jumpHelper, 2);
        assertEquals(jumpHelper.getJumpIncrease(), (jumpHeight/(jumpDuration/2)));
    }

    @Test
    public void testJump_1() {
        Pos startPos = pos;
        jumpHelper.jump(pos);
        jumpHelper.tick();
        assertEquals(pos, startPos.add(new Pos(0, (jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_2() {
        Pos startPos = pos;
        jumpHelper.jump(pos);
        tick(this.jumpHelper, 2);
        assertEquals(pos, startPos.add(new Pos(0, 2*(jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_Highest() {
        Pos startPos = pos;
        jumpHelper.jump(pos);
        tick(this.jumpHelper, jumpDuration/2);
        assertEquals(pos, startPos.add(new Pos(0, jumpHeight)));
    }

    @Test
    public void testJump_Highest_Down_1() {
        Pos startPos = pos;
        jumpHelper.jump(pos);
        tick(this.jumpHelper, (jumpDuration/2) + 1);
        assertEquals(pos, startPos.add(new Pos(0, jumpHeight - (jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_Highest_Down_2() {
        Pos startPos = pos;
        jumpHelper.jump(pos);
        tick(this.jumpHelper, (jumpDuration/2) + 2);
        assertEquals(pos, startPos.add(new Pos(0, jumpHeight - 2*(jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_Lowest() {
        Pos startPos = pos;
        jumpHelper.jump(pos);
        tick(this.jumpHelper, jumpDuration);
        assertEquals(pos, startPos.add(new Pos(0, 0)));
    }

    @Test
    public void testJump_Lowest_jumpingFalse() {
        jumpHelper.jump(pos);
        tick(this.jumpHelper, jumpDuration+1);
        assertFalse(jumpHelper.isJumping());
        assertEquals(pos, new Pos(0, 0));
    }

    @Test
    public void testGetValue() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, updatePositionListener());
        assertEquals(jumpState.getModulo(), 79);
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_1_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, updatePositionListener());
        jumpState.jump(pos);
        tick(jumpState, 1);
        assertEquals(this.pos, new Pos(0,0));
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_5_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, updatePositionListener());
        jumpState.jump(pos);
        tick(jumpState, 5);
        System.out.println("Pos: " + this.pos.getFormat());
        assertEquals(this.pos, new Pos(0,0));
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_76_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, updatePositionListener());
        jumpState.jump(pos);
        tick(jumpState, 76);
        assertEquals(this.pos, new Pos(0,0));
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_79_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, updatePositionListener());
        jumpState.jump(pos);
        tick(jumpState, 80);
        assertEquals(this.pos, new Pos(0,1));
    }

    private Consumer<Pos> updatePositionListener() {
        return (addPos) -> {
            this.pos = this.pos.add(addPos);
        };
    }

    private void tick(PropertyJumpState jumpState, int amount) {
        for(int i = 0; i < amount; i++)
            jumpState.tick();
    }

}
