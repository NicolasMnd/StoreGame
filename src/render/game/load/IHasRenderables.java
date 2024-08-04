package render.game.load;

import game.GameObject;
import game.state.GameState;
import render.game.camera.Camera;

import java.util.List;

public interface IHasRenderables {

    List<GameObject> getRenderables(GameState state, Camera camera);

}
