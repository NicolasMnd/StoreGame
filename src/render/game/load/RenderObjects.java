package render.game.load;

import game.GameObject;
import game.state.GameState;
import render.game.camera.Camera;
import render.game.renderorder.RenderableScreenObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RenderObjects {

    private List<IHasRenderables> renderables;

    public RenderObjects() {
        this.renderables = List.of(
                new RenderTiles(),
                new RenderPlayer(),
                new RenderAnimations(),
                new RenderHeldItems()
        );
    }

    /**
     * For a given {@link GameState}, get all objects that are in scope to be rendered.
     * @param state the game state to be rendered
     * @return a list of {@link GameObject}s that should be rendered on the screen
     */
    public List<RenderableScreenObject> getRenderObjects(GameState state, Camera camera) {

        List<RenderableScreenObject> gameObjects = new ArrayList<>();

        for(IHasRenderables element : renderables)
            gameObjects.addAll(element.getRenderables(state, camera));

        gameObjects.sort(Comparator.comparingInt(obj -> obj.getRenderStage().getStage()));

        return gameObjects;

    }



}
