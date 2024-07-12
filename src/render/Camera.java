package render;

import game.GameObject;
import game.tile.GameTile;
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
        System.out.println("Start: " + start.getFormat() + " diff: " + start.x() + " - " + (cameraWidth / 2) + " = " + (start.x() - (cameraWidth / 2)));
        this.camera = new Hitbox(
                new Pos(start.x() - (cameraWidth / 2), start.y() - (cameraHeight / 2)),
                new Pos(start.x() + (cameraWidth / 2), start.y() + (cameraHeight / 2))
        );
        System.out.println("Hitbox: " + camera.getPrint());
        this.tileSize = tileSize;
    }

    /**
     * Retrieves a list of {@link Camera.RenderedGameTile} objects, which has render positions.
     * @param originalMap the {@link GameTile} double array which is inspected to fit the camera
     * @param mapTileSize the extra amplification of the game size.
     * @return a list of {@link Camera.RenderedGameTile} objects
     */
    public GameObject[][] getRenderTiles(GameTile[][] originalMap, double mapTileSize) {

        int tileSize = (int) (this.tileSize * mapTileSize);

        // To prevent the map being drawn per tile, this would allow small shifts so only part of a tile can be displayed
        int xResidu = middle.x() % tileSize;
        int yResidu = middle.y() % tileSize;

        // We calculate indexes to find tiles overlapping with the camera.
        // Directly calling the hitbox positions can save up about 10k nanoseconds!
        int iStart = floor(camera.getUpperleft().y(), tileSize);
        int iEnd = floor(camera.getLowerright().y(), tileSize);
        int jStart = floor(camera.getUpperleft().x(), tileSize);
        int jEnd = floor(camera.getLowerright().x(), tileSize);

        int screenX = -3, screenY = -3;
        int line = 0, col = 0;

        GameObject[][] tiles = new GameObject[(iEnd - iStart)][(jEnd - jStart)];

        for (int i = iStart; i < iEnd; i++) {

            col = 0;

            for (int j = jStart; j < jEnd; j++) {

                Pos drawPos = new Pos((screenX * tileSize) - xResidu, (screenY * tileSize) - yResidu);

                if (i < 0 || j < 0)
                    tiles[line][col] = new RenderedGameTile(tileSize, drawPos);

                else if (i >= 0 && j >= 0 && i < originalMap.length && j < originalMap[i].length)
                    tiles[line][col] = new RenderedGameTile(originalMap[i][j], drawPos);


                col++;
                screenX += 1;

            }

            screenY += 1;
            screenX = -3;
            line++;

        }

        printTiles(tiles);

        return tiles;
    }

    /**
     * Modifies the game size
     * @return
     */
    public void modifySize(int newSize) {

    }

    /**
     * Checks how many times b fits in a.
     */
    private int floor(int a, int b) {
        return Math.floorDiv(a, b);
    }

    private void printTiles(GameObject[][] l) {
        for (GameObject[] o : l) {
            for (GameObject oo : o)
                if (oo != null)
                    System.out.print(oo.getTexture() + ", ");
                else
                    System.out.print("null, ");
            System.out.println();
        }
    }

    /**
     * Decorator class for {@link GameObject}.
     */
    public static class RenderedGameTile extends GameObject {
        private final GameObject parent;
        private final Pos renderPosition;
        private int tileSize = 0;

        public RenderedGameTile(GameObject parent, Pos renderPosition) {
            super(parent.getPosition());
            this.parent = parent;
            this.renderPosition = renderPosition;
            setTexture(textureLoader(new TextureLoader()).loadTexture());
            this.setHeight(parent.getHeight());
            this.setWidth(parent.getWidth());
        }

        public RenderedGameTile(int tileSize, Pos renderPosition) {
            super(null);
            this.tileSize = tileSize;
            this.parent = null;
            this.renderPosition = renderPosition;
            setTexture(textureLoader(new TextureLoader()).loadTexture());
            this.setHeight(32);
            this.setWidth(32);
        }

        @Override
        public Pos getPosition() {
            return this.renderPosition;
        }

        @Override
        public ITextureLoader textureLoader(TextureLoader textureLoader) {
            if(parent == null) return () -> null;
            return parent.textureLoader(textureLoader);
        }

        @Override
        public ITextureStrategy textureSelector(TextureSelector selector) {
            if(parent == null) return () -> null;/*((Texture) (Texture.getBackground(tileSize))).getImage();*/
            return parent.textureSelector(selector);
        }
    }

}
