package game.entity.property;

import listeners.IMoveValidity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.positions.Pos;
import util.positions.Hitbox;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
        this.jumpHelper = new PropertyJumpState(jumpHeight, jumpDuration, getMoveChecker(), updatePositionListener());
        this.pos = new Pos(0,0);
    }

    @Test
    public void testJump_StartY() {
        this.jumpHelper.jump(() -> pos, hitboxSupplier());
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
        jumpHelper.jump(() -> pos, hitboxSupplier());
        jumpHelper.tick();
        assertEquals(pos, startPos.add(new Pos(0, -(jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_2() {
        Pos startPos = pos;
        jumpHelper.jump(() -> pos, hitboxSupplier());
        tick(this.jumpHelper, 2);
        assertEquals(pos, startPos.add(new Pos(0, -2*(jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_Highest() {
        Pos startPos = pos;
        jumpHelper.jump(() -> pos, hitboxSupplier());
        tick(this.jumpHelper, jumpDuration/2);
        assertEquals(pos, startPos.add(new Pos(0, -jumpHeight)));
    }

    @Test
    public void testJump_Highest_Down_1() {
        Pos startPos = pos;
        jumpHelper.jump(() -> pos, hitboxSupplier());
        tick(this.jumpHelper, (jumpDuration/2) + 1);
        assertEquals(pos, startPos.add(new Pos(0, -jumpHeight + (jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_Highest_Down_2() {
        Pos startPos = pos;
        jumpHelper.jump(() -> pos, hitboxSupplier());
        tick(this.jumpHelper, (jumpDuration/2) + 2);
        assertEquals(pos, startPos.add(new Pos(0, -jumpHeight + 2*(jumpHeight/(jumpDuration/2)))));
    }

    @Test
    public void testJump_Lowest() {
        Pos startPos = pos;
        jumpHelper.jump(() -> pos, hitboxSupplier());
        tick(this.jumpHelper, jumpDuration);
        assertEquals(pos, startPos.add(new Pos(0, 0)));
    }

    @Test
    public void testJump_Lowest_jumpingFalse() {
        jumpHelper.jump(() -> pos, hitboxSupplier());
        tick(this.jumpHelper, jumpDuration+1);
        assertFalse(jumpHelper.isJumping());
        assertEquals(pos, new Pos(0, 0));
    }

    @Test
    public void testGetValue() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, getMoveChecker(), updatePositionListener());
        assertEquals(jumpState.getModulo(), 79);
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_1_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, getMoveChecker(), updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 1);
        assertEquals(this.pos, new Pos(0,0));
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_5_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, getMoveChecker(), updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 5);
        assertEquals(this.pos, new Pos(0,0));
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_76_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, getMoveChecker(), updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 76);
        assertEquals(this.pos, new Pos(0,0));
    }

    @Test
    public void testIncreaseValueAfter_ModuloTicks_79_tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, getMoveChecker(), updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 80);
        assertEquals(this.pos, new Pos(0,-1));
    }

    @Test
    public void hitboxJump_MaxHeight_Tick() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 32, (box) -> box.getLowerright().y() > -5, updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 2);
        assertEquals(pos, new Pos(0, -4));
    }

    @Test
    public void hitboxJump_MaxHeight_SameHeightAfterMax() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 32, (box) -> box.getLowerright().y() > -5, updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 3);
        assertEquals(pos, new Pos(0, -4));
    }

    @Test
    public void hitboxJump_MaxHeight_SameHeightAfterMax2() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 32, (box) -> box.getLowerright().y() > -5, updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 4);
        assertEquals(pos, new Pos(0, -2));
    }

    @Test
    public void hitboxJump_MaxHeight_DescendingAfter() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 32, (box) -> box.getLowerright().y() > -5, updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 7);
        assertEquals(pos, new Pos(0, 0));
    }

    @Test
    public void hitboxJump_soonerDown() {
        PropertyJumpState jumpState = new PropertyJumpState(32, 5000, (box) -> box.getLowerright().y() > -5, updatePositionListener());
        jumpState.jump(() -> pos, hitboxSupplier());
        tick(jumpState, 11);
        assertEquals(pos, new Pos(0,0));
    }

    private Consumer<Pos> updatePositionListener() {
        return (addPos) -> {
            this.pos = this.pos.add(addPos);
        };
    }

    private IMoveValidity getMoveChecker() {
        return (pos) -> true;
    }

    private Supplier<Hitbox> hitboxSupplier() {
        return () -> new Hitbox(new Pos(0, 0), new Pos(0, 0));
    }

    private void tick(PropertyJumpState jumpState, int amount) {
        for(int i = 0; i < amount; i++)
            jumpState.tick();
    }

}
