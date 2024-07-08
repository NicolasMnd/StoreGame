package render;

import game.GameObject;
import game.tile.GameTile;
import util.Direction;
import util.Pos;
import util.hitbox.Hitbox;
import util.texture.TextureLoader;
import util.texture.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class Camera {

    private Pos middle;
    private Hitbox camera;
    private final int tileSize;

    public Camera(Pos start, int cameraWidth, int cameraHeight, int tileSize) {
        this.middle = start;
        this.camera = new Hitbox(
                new Pos(start.x() - (cameraWidth*tileSize/2), start.y() - (cameraHeight*tileSize/2)),
                new Pos(start.x() + (cameraWidth*tileSize/2), start.y() + (cameraHeight*tileSize/2))
        );
        this.tileSize = tileSize;
    }

    /**
     * Retrieves a list of {@link Camera.RenderedGameTile} objects, which has render positions.
     */
    public GameObject[][] getRenderTiles(GameTile[][] originalMap) {

        // To prevent the map being drawn per tile, this would allow small shifts so only part of a tile can be displayed
        int xResidu = middle.x() % tileSize;
        int yResidu = middle.y() % tileSize;

        // We calculate indexes to find tiles overlapping with the camera.
        // Directly calling the hitbox positions can save up about 10k nanoseconds!
        int iStart = floor(camera.getUpperleft().y(), tileSize) - 1;
        int iEnd = floor(camera.getLowerright().y(), tileSize) + 1;
        int jStart = floor(camera.getUpperleft().x(), tileSize) - 1;
        int jEnd = floor(camera.getLowerright().x(), tileSize) + 1;

        int screenX = -1, screenY = -1;
        int line = 0, col = 0;

        GameObject[][] tiles = new GameTile[(iEnd - iStart)][(jEnd - jStart)];

        for(int i = iStart; i < iEnd; i++) {

            col = 0;

            for(int j = jStart; j < jEnd; j++) {

                Pos drawPos = new Pos((screenX * tileSize) - xResidu, (screenY * tileSize) - yResidu);

                if(i < 0 || j < 0) {
                    tiles[line][col] = null;
                } else if(i < originalMap.length && j < originalMap[i].length) {
                    tiles[line][col] = new RenderedGameTile(originalMap[line][col], drawPos);
                }

                col++;

            }

            line++;

        }

        return tiles;
    }

    /**
     * Checks how many times b fits in a.
     */
    private int floor(int a, int b) {
        int fd = Math.floorDiv(a, b);
        return fd;
    }

    /**
     * Decorator class for {@link GameObject}.
     */
    public static class RenderedGameTile extends GameObject {
        private final GameObject parent;
        private final Pos renderPosition;

        public RenderedGameTile(GameObject parent, Pos renderPosition) {
            super(parent.getPosition());
            this.parent = parent;
            this.renderPosition = renderPosition;
        }

        @Override
        public Pos getPosition() {
            return this.renderPosition;
        }

        @Override
        public ITextureLoader textureLoader(TextureLoader textureLoader) {
            return parent.textureLoader(textureLoader);
        }

        @Override
        public ITextureStrategy textureSelector(TextureSelector selector) {
            return parent.textureSelector(selector);
        }
    }

}
