package render.game.load;

import game.ScreenObject;
import game.state.GameState;
import render.game.camera.Camera;
import render.game.renderorder.RenderableScreenObject;
import util.texture.animation.Animation;

import java.util.ArrayList;
import java.util.List;

public class RenderAnimations implements IHasRenderables {

    @Override
    public List<RenderableScreenObject> getRenderables(GameState state, Camera camera) {
        return this.getScreenObjects(state, camera);
    }

    List<RenderableScreenObject> getScreenObjects(GameState state, Camera camera) {

        List<ScreenObject> animation = state.getAnimations().stream().map(Animation::getScreenObject).toList();
        List<RenderableScreenObject> objects = new ArrayList<>();

        animation.forEach(
                (screenObject) -> {

                    if(camera.getRealCamera(state.getMapDimensions()).hasOverlap(screenObject.getHitbox())) {

                        //System.out.println("Setting print location for " + screenObject.getClass().getName() + " to " + camera.getRenderPosition(screenObject.getPosition()).getFormat());
                        objects.add(new RenderableScreenObject(screenObject, camera.getRenderPosition(screenObject.getPosition(), state.getMapDimensions())));

                    }

                }
        );

        return objects;

    }

}
