package render.game.load;

import game.state.GameState;
import render.game.renderorder.RenderableGameObject;
import render.game.camera.Camera;

import java.util.List;

public interface IHasRenderables {

    List<RenderableGameObject> getRenderables(GameState state, Camera camera);

}
