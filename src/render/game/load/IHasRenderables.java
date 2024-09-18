package render.game.load;

import game.state.GameState;
import render.game.camera.Camera;
import render.game.renderorder.RenderableScreenObject;

import java.util.List;

public interface IHasRenderables {

    List<RenderableScreenObject> getRenderables(GameState state, Camera camera);

}
