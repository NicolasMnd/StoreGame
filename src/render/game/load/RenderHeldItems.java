package render.game.load;

import game.item.GameItem;
import game.item.vegetables.ItemTomato;
import game.state.GameState;
import render.game.camera.Camera;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderableScreenObject;
import util.Direction;
import util.positions.Pos;

import java.util.List;
import java.util.function.Supplier;

public class RenderHeldItems implements IHasRenderables {

    @Override
    public List<RenderableScreenObject> getRenderables(GameState state, Camera camera) {
        return getItems(state, camera);
    }

    private List<RenderableScreenObject> getItems(GameState state, Camera camera) {

        RenderStage stage = new RenderPlayer().getRenderStage(state);

        List<RenderableScreenObject> list =  List.of(
            getItem(
                    () -> state.getPlayer().getLimbTracker().getLeftHand(),
                    () -> camera.getRenderPositionFocused(state.getMapDimensions()),
                    state.getPlayer().getInventory().getItemLefthand(),
                    Direction.LEFT,
                    state.getPlayer().getFacing()
            )
        );

        if(stage == RenderStage.PLAYER_SHADOW)
            for(RenderableScreenObject item : list)
                item.setRenderOrder(stage);

        return list;
    }

    private RenderableScreenObject getItem(Supplier<Pos> limbPosition, Supplier<Pos> cameraPosition, GameItem item, Direction sideHand, Direction playerDirection) {
        RenderableScreenObject renderedItem =  new RenderableScreenObject(
                new ItemTomato(),
                cameraPosition.get()
                        .add(limbPosition.get())
                        .add(new Pos(0, item.getYStart()))
                );

        renderedItem.setRenderOrder(determineRenderOrder(sideHand, playerDirection));

        return renderedItem;
    }

    private RenderStage determineRenderOrder(Direction sideHand, Direction facing) {
        if(
                (sideHand == Direction.RIGHT && facing == Direction.LEFT)
                ||
                (sideHand == Direction.LEFT && facing == Direction.RIGHT)
        ) {
            return RenderStage.PLAYER_SUB;
        }

        return RenderStage.PLAYER_SUPER;
    }

}
