package render.game.load;

import game.GameObject;
import game.state.GameState;
import render.game.RenderableGameObject;
import render.game.camera.Camera;

import java.util.List;

public class RenderPlayer implements IHasRenderables {

    @Override
    public List<GameObject> getRenderables(GameState state, Camera camera) {
        return List.of(
                new RenderableGameObject(state.getPlayer(), camera.getRenderPosition(state.getTiles()[0].length, state.getTiles().length))
        );
    }

}
