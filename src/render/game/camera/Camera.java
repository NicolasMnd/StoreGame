package render.game.camera;

import game.ScreenObject;
import render.View;
import util.Dimension;
import util.Logger;
import util.positions.Hitbox;
import util.positions.Pos;

public class Camera {

    private ScreenObject focused;
    private final int tileSize;
    private int cameraHeight, cameraWidth;
    private Logger logger = new Logger("render times");
    private Dimension mapSize;

    public Camera(ScreenObject focused, View view) {
        this.focused = focused;
        this.tileSize = view.getTileSize();
        this.cameraWidth = view.getDimension().getWidth();
        this.cameraHeight = view.getDimension().getHeight();
    }

    /**
     * @return The middle of the camera
     */
    public Pos getCenter() {
        return focused.getPosition();
    }

    /**
     * Updates the {@link Hitbox}
     * @param object the new focused object
     */
    public void focus(ScreenObject object) {
        this.focused = object;
    }

    /**
     * @return the render {@link Pos} for the focused object.
     */
    public Pos getRenderPositionFocused(Dimension mapSize) {
        Hitbox realCamera = getRealCamera(mapSize);

        // If clamping on bounds was not necessary
        if(this.focused.getPosition().equals(realCamera.getCenterPos()))

            return new Pos(cameraWidth/2, cameraHeight/2);

        else {

            int drawX = getCenter().x() - realCamera.getUpperleft().x();
            int drawY = getCenter().y() - realCamera.getUpperleft().y();
            return new Pos(drawX, drawY);

        }

    }

    public Pos getRenderPosition(Pos pos, Dimension mapSize) {
        return pos.subtract(this.getRealCamera(mapSize).getUpperleft());
    }

    /**
     * Returns a camera hit box, which overlaps coordinates that are in the scope of the JFrame window
     */
    public Hitbox getCameraOverlap() {
        Pos center = focused.getPosition();
        return new Hitbox(
                new Pos(center.x() - (cameraWidth / 2), center.y() - (cameraHeight / 2)),
                new Pos(center.x() + (cameraWidth / 2), center.y() + (cameraHeight / 2))
        );
    }

    /**
     * Returns the real camera, which is clamped to the bounds of the map.
     * @param mapSize the dimensions of the tile map
     * @return a {@link Hitbox} that is clamped to the bounds of the map
     */
    public Hitbox getRealCamera(Dimension mapSize) {

        int lengthRow = mapSize.getHeight()+1;
        int lengthColumn = mapSize.getWidth()+1;
        Hitbox hitBox = getCameraOverlap();

        int adjustedUpperX = hitBox.getUpperleft().x();
        int adjustedUpperY = hitBox.getUpperleft().y();
        int adjustedLowerX = hitBox.getLowerright().x();
        int adjustedLowerY = hitBox.getLowerright().y();

        // First case:
        if (adjustedUpperX < 0) {
            int difference = -adjustedUpperX;
            adjustedUpperX = 0;
            adjustedLowerX = adjustedLowerX + difference;
            //System.out.println("Found upper left x smaller than 0. Changed to zero and lower right X = " + adjustedLowerX + " (old = " + (adjustedLowerX-difference));
        }
        if (adjustedUpperY < 0) {
            int difference = -adjustedUpperY;
            adjustedUpperY = 0;
            adjustedLowerY = adjustedLowerY + difference;
            //System.out.println("Found upper left y smaller than 0. Changed to zero and lower right Y = " + adjustedLowerY + " (old = " + (adjustedLowerY-difference));
        }
        if (adjustedLowerX > lengthRow * tileSize) {
            int difference = adjustedLowerX - (lengthRow * tileSize);
            adjustedLowerX = lengthRow * tileSize;
            adjustedUpperX = adjustedUpperX - difference;
            //System.out.println("Found lower left x greater than " + (lengthRow*tileSize) + ". Changed to " + (lengthRow*tileSize) + " and lower right Y = " + adjustedUpperX + " (old = " + (adjustedUpperX+difference));
        }
        if (adjustedLowerY > lengthColumn * tileSize) {
            int difference = adjustedLowerY - (lengthColumn * tileSize);
            adjustedLowerY = lengthColumn * tileSize;
            adjustedUpperY = adjustedUpperY - difference;
            //System.out.println("Found lower left y greater than " + (lengthColumn*tileSize) + ". Changed to " + (lengthColumn*tileSize) + " and lower right Y = " + adjustedUpperY + " (old = " + (adjustedUpperX+difference));
        }

        return new Hitbox(new Pos(adjustedUpperX, adjustedUpperY), new Pos(adjustedLowerX, adjustedLowerY));
    }

    int getOffsetX() {
        return this.focused.getWidth()/2;
    }

    int getOffsetY() {
        return this.focused.getHeight()/2;
    }

}
