package render;

import game.GameObject;
import game.tile.GameTile;
import util.Pos;
import util.hitbox.Hitbox;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.IRender;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;
import util.texture.textureinformation.RenderStrategy;

public class Camera {

    private Pos center;
    private Hitbox camera;
    private final int tileSize;
    private Pos latestZeroPos;

    public Camera(Pos center, View view) {
        this.center = center;
        this.tileSize = view.getTileSize();
        int cameraWidth = view.getDimension().getWidth();
        int cameraHeight = view.getDimension().getHeight();
        double gameSize = view.getGameSize();
        this.camera = new Hitbox(
                new Pos(center.x() - (int) (cameraWidth*gameSize / 2), center.y() - (int) (cameraHeight*gameSize / 2)),
                new Pos(center.x() + (int) (cameraWidth*gameSize / 2), center.y() + (int) (cameraHeight*gameSize / 2))
        );
    }

    /**
     * @return The middle of the camera
     */
    public Pos getCenter() {
        return this.camera.getCenterPos();
    }

    /**
     * Updates the {@link Hitbox}
     * @param center the new center of the camera
     * @param view the {@link View} reference which has details about {@link GameView#getGameSize()}
     */
    public void updateCenter(Pos center, GameView view) {
        int cameraWidth = view.getDimension().getWidth();
        int cameraHeight = view.getDimension().getHeight();
        double gameSize = view.getGameSize();
        this.center = center;
        this.camera = new Hitbox(
                new Pos(center.x() - (int) (cameraWidth*gameSize / 2), center.y() - (int) (cameraHeight*gameSize / 2)),
                new Pos(center.x() + (int) (cameraWidth*gameSize / 2), center.y() + (int) (cameraHeight*gameSize / 2))
        );
    }

    /**
     * Retrieves a list of {@link Camera.RenderedGameTile} objects, which has render positions.
     * @param originalMap the {@link GameTile} double array which is inspected to fit the camera
     * @param mapTileSize the extra amplification of the game size.
     * @return a double array of {@link Camera.RenderedGameTile} objects
     */
    public GameObject[][] getRenderTiles(GameTile[][] originalMap, double mapTileSize) {

        int tileSize = (int) (this.tileSize * mapTileSize);

        // To prevent the map being drawn per tile, this would allow small shifts so only part of a tile can be displayed
        int xResidu = center.x() % (tileSize);
        int yResidu = center.y() % (tileSize);

        int iStart = floor(camera.getUpperleft().y(), tileSize)-3;
        int iEnd = floor(camera.getLowerright().y(), tileSize)+3;
        int jStart = floor(camera.getUpperleft().x()+1, tileSize)-3;
        int jEnd = floor(camera.getLowerright().x()+1, tileSize)+3;

        int screenX = -3, screenY = -3;
        int line = 0, col = 0;

        GameObject[][] tiles = new GameObject[(iEnd - iStart)][(jEnd - jStart)];

        for (int i = iStart; i < iEnd; i++) {

            col = 0;

            for (int j = jStart; j < jEnd; j++) {

                Pos drawPos = new Pos((screenX * tileSize) - xResidu, (screenY * tileSize) - yResidu);
                if(i == 0 && j == 0) {
                    System.out.println("DrawPos: " + drawPos.getFormat() + screenX + " * " + tileSize + " - " + xResidu + " = " + ((screenX *tileSize) - xResidu) + " ; lowerRightX: " + camera.getLowerright().x());
                    System.out.println("jStart = " + jStart + " ---> " + (camera.getUpperleft().x()+1) + " / " + tileSize + " = " + ((double) (camera.getUpperleft().x()+1)/tileSize) + " \n");
                }

                if (i < 0 || j < 0)
                    tiles[line][col] = new RenderedGameTile(tileSize, drawPos);

                else if (i >= 0 && j >= 0 && i < originalMap.length && j < originalMap[i].length)
                    tiles[line][col] = new RenderedGameTile(originalMap[i][j], drawPos);

                else
                    tiles[line][col] = new RenderedGameTile(tileSize, drawPos);

                col++;
                screenX += 1;

            }

            screenY += 1;
            screenX = -3;
            line++;

        }

        return tiles;
    }

    /**
     * Checks how many times b fits in a.
     */
    int floor(int a, int b) {
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
        final Pos renderPosition;
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

        GameObject parent() {
            return this.parent;
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

        @Override
        public IRender getRenderStrategy() {
            if(this.textureSelector(new TextureSelector()).retrieveTexture() == null)
                return new RenderStrategy().rectangleRenderer(this);
            else
                return new RenderStrategy().imageRenderer(this);
        }

    }

    private Save save(int residu, int a, int b, int c, int d) {
        return new Save(center, camera, residu, a, b, c, d);
    }

    private Save save(int residu, int a, int b, int c, int d, GameObject[][] t) {
        return new Save(center, camera, residu, a, b, c, d, t);
    }

}
