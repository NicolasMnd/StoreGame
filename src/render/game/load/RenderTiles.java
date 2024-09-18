package render.game.load;

import game.GameObject;
import game.state.GameState;
import game.tile.GameTile;
import render.game.camera.Camera;
import render.game.renderorder.RenderableScreenObject;
import util.positions.Hitbox;
import util.positions.Pos;

import java.util.ArrayList;
import java.util.List;

class RenderTiles implements IHasRenderables {

    @Override
    public List<RenderableScreenObject> getRenderables(GameState state, Camera camera) {
        return getRenderTiles(camera.getRealCamera(state.getMapDimensions()), state.getTiles(), state.getTileSize());
    }

    /**
     * Retrieves a list of {@link RenderableScreenObject} objects, which has render positions.
     * [50,000 -> 500,000] nanoseconds time consumption Mostly around 100-200k
     * @return a double array of {@link RenderableScreenObject} objects
     */
    public List<RenderableScreenObject> getRenderTiles(Hitbox camera, GameTile[][] originalMap, int tileSize) {

        // To prevent the map being drawn per tile, this would allow small shifts so only part of a tile can be displayed
        int xResidu = camera.getCenterPos().x() % tileSize;
        int yResidu = camera.getCenterPos().y() % tileSize;
        int spacing = 2;

        // We calculate indexes to find tiles overlapping with the camera.
        int iStart = floor(camera.getUpperleft().y(), tileSize) - spacing;
        int iEnd = floor(camera.getLowerright().y(), tileSize) + spacing;
        int jStart = floor(camera.getUpperleft().x(), tileSize) - spacing;
        int jEnd = floor(camera.getLowerright().x(), tileSize) + spacing;

        // We create a list with all our possible tiles
        List<RenderableScreenObject> tiles = new ArrayList<>();

        // Helper variables
        int count = 0;
        int screenX = -spacing, screenY = -spacing;

        // We find all tiles
        for(int i = iStart; i < iEnd; i++) {

            for(int j = jStart; j < jEnd; j++) {

                Pos drawPos = new Pos((screenX * tileSize) - xResidu, (screenY * tileSize) - yResidu);

                if (i >= 0 && j >= 0 && i < originalMap.length && j < originalMap[i].length) {
                    tiles.add(new RenderableScreenObject(originalMap[i][j], drawPos));
                }
                else {
                    tiles.add(new RenderableScreenObject(tileSize, drawPos));
                }

                screenX += 1;

            }

            screenY += 1;
            screenX = -spacing;

        }

        return tiles;
    }

    private GameObject[][] getMap(GameObject[] tiles, int amountRow, int amountColumn) {
        GameObject[][] map = new GameObject[amountRow+1][amountColumn+1];

        int col = 0, row = 0;
        for(int i = 0; i < tiles.length; i++) {
            map[row][col] = tiles[i];

            if(i % amountColumn == 0) {
                col = 0;
                row++;
            } else
                col++;
        }

        return map;
    }

    /**
     * Checks how many times b fits in a.
     */
    int floor(int a, int b) {
        return Math.floorDiv(a, b);
    }

}
