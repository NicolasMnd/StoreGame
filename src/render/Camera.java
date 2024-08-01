package render;

import game.GameObject;
import game.tile.GameTile;
import util.Logger;
import util.OperationTime;
import util.positions.Pos;
import util.positions.Hitbox;

public class Camera {

    private Pos center;
    private Hitbox camera;
    private final int tileSize;
    private Logger logger = new Logger("render times");

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
     * Retrieves a list of {@link RenderableGameObject} objects, which has render positions.
     * [50,000 -> 500,000] nanoseconds time consumption Mostly around 100-200k
     * @param originalMap the {@link GameTile} double array which is inspected to fit the camera
     * @param mapTileSize the extra amplification of the game size.
     * @return a double array of {@link RenderableGameObject} objects
     */
    public GameObject[][] getRenderTiles(GameTile[][] originalMap, double mapTileSize) {
        OperationTime time = new OperationTime("Rendering");
        time.start();
        int tileSize = (int) (mapTileSize * this.tileSize);

        // To prevent the map being drawn per tile, this would allow small shifts so only part of a tile can be displayed
        int xResidu = camera.getCenterPos().x() % tileSize;
        int yResidu = camera.getCenterPos().y() % tileSize;

        // We calculate indexes to find tiles overlapping with the camera.
        // Directly calling the hitbox positions can save up about 10k nanoseconds!
        int iStart = floor(camera.getUpperleft().y(), tileSize) - 3;
        int iEnd = floor(camera.getLowerright().y(), tileSize) + 3;
        int jStart = floor(camera.getUpperleft().x(), tileSize) - 3;
        int jEnd = floor(camera.getLowerright().x(), tileSize) + 3;

        // We create a list with all our possible tiles
        GameObject[] tiles = new GameObject[(iEnd - iStart)*(jEnd - jStart)];

        // Helper variables
        int count = 0;
        int screenX = -3, screenY = -3;

        // We find all tiles
        for(int i = iStart; i < iEnd; i++) {

            for(int j = jStart; j < jEnd; j++) {

                Pos drawPos = new Pos((screenX * tileSize) - xResidu, (screenY * tileSize) - yResidu);

                if (i >= 0 && j >= 0 && i < originalMap.length && j < originalMap[i].length) {
                    tiles[count++] = new RenderableGameObject(originalMap[i][j], drawPos);
                }
                else {
                    tiles[count++] = new RenderableGameObject(tileSize, drawPos);
                }

                screenX += 1;

            }

            screenY += 1;
            screenX = -3;

        }
        time.stop();
        logger.time(time.getNano());
        return getMap(tiles, (iEnd-iStart), (jEnd-jStart));
    }

    public GameObject[][] getMap(GameObject[] tiles, int amountRow, int amountColumn) {
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

}
