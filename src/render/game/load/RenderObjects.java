package render.game.load;

import game.GameObject;
import game.state.GameState;
import render.game.camera.Camera;

import java.util.ArrayList;
import java.util.List;

public class RenderObjects {

    private List<IHasRenderables> renderables;

    public RenderObjects() {
        this.renderables = List.of(
                new RenderTiles(),
                new RenderPlayer()
        );
    }

    /**
     * For a given {@link GameState}, get all objects that are in scope to be rendered.
     * @param state the game state to be rendered
     * @return a list of {@link GameObject}s that should be rendered on the screen
     */
    public List<GameObject> getRenderObjects(GameState state, Camera camera) {

        List<GameObject> gameObjects = new ArrayList<>();

        for(IHasRenderables element : renderables)
            gameObjects.addAll(element.getRenderables(state, camera));

        return gameObjects;

    }



}
