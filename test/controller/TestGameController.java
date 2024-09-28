package controller;

import game.entity.types.Entity;
import org.junit.jupiter.api.Test;
import util.Direction;
import util.positions.Pos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestGameController {

    @Test
    public void testCreateGameController() {
        GameController controller = new GameController();
        controller.idle(null);
        controller.update();
    }

    @Test
    public void testMovePlayer_PositionUpdated() {
        GameController controller = new GameController();
        Pos p = controller.facade.state.getPlayer().getPosition();
        controller.facade.playerCommand((player) -> player.move(Direction.LEFT));
        assertNotEquals(p, controller.facade.state.getPlayer().getPosition());
    }

    @Test
    public void testMovePlayer_PositionLeft() {
        GameController controller = new GameController();
        Entity entity = controller.facade.state.getPlayer();
        Pos p = controller.facade.state.getPlayer().getPosition();

        controller.facade.playerCommand((player) -> player.move(Direction.LEFT));
        assertEquals(p.add(new Pos(-entity.getSpeed(),0)), controller.facade.state.getPlayer().getPosition());
    }

    @Test
    public void testMovePlayer_PositionRight() {
        GameController controller = new GameController();
        Entity entity = controller.facade.state.getPlayer();
        Pos p = controller.facade.state.getPlayer().getPosition();

        controller.facade.playerCommand((player) -> player.move(Direction.LEFT));
        controller.facade.playerCommand((player) -> player.move(Direction.LEFT));
        controller.facade.playerCommand((player) -> player.move(Direction.RIGHT));
        assertEquals(p.add(new Pos(-entity.getSpeed(), 0)), entity.getPosition());
    }

    @Test
    public void testMovePlayer_PositionDown() {
        GameController controller = new GameController();
        Entity entity = controller.facade.state.getPlayer();
        Pos p = controller.facade.state.getPlayer().getPosition();

        controller.facade.playerCommand((player) -> player.move(Direction.LEFT));
        controller.facade.playerCommand((player) -> player.move(Direction.DOWN));
        assertEquals(p.add(new Pos(-entity.getSpeed(), entity.getSpeed())), entity.getPosition());
    }

    @Test
    public void testMovePlayer_PositionUp() {
        GameController controller = new GameController();
        Entity entity = controller.facade.state.getPlayer();
        Pos p = controller.facade.state.getPlayer().getPosition();

        controller.facade.playerCommand((player) -> player.move(Direction.LEFT));
        controller.facade.playerCommand((player) -> player.move(Direction.UP));
        assertEquals(p.add(new Pos(-entity.getSpeed(), -entity.getSpeed())), entity.getPosition());
    }

}
