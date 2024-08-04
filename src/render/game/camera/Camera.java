package render.game.camera;

import game.GameObject;
import render.View;
import util.Logger;
import util.positions.Hitbox;
import util.positions.Pos;

public class Camera {

    private GameObject focused;
    private final int tileSize;
    private int cameraHeight, cameraWidth;
    private Logger logger = new Logger("render times");

    public Camera(GameObject focused, View view) {
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
     * Updates the size of the window to the camera class.
     * @param view the {@link View} object that has changed dimensions
     */
    public void updateView(View view) {
        this.cameraHeight = view.getDimension().getHeight();
        this.cameraWidth = view.getDimension().getWidth();
    }

    /**
     * Updates the {@link Hitbox}
     * @param object the new focused object
     */
    public void focus(GameObject object) {
        this.focused = object;
    }

    /**
     * @return the render {@link Pos} for the focused object.
     */
    public Pos getRenderPosition(int lengthRow, int lengthColumn) {
        Hitbox realCamera = getRealCamera(lengthRow, lengthColumn);

        // If clamping on bounds was not necessary
        if(this.focused.getPosition().equals(realCamera.getCenterPos()))
            return new Pos(cameraWidth/2 - focused.getWidth()/2, cameraHeight/2 - focused.getHeight()/2);

        else {

            int drawX = getCenter().x() - realCamera.getUpperleft().x() - focused.getWidth()/2;
            int drawY = getCenter().y() - realCamera.getUpperleft().y() - focused.getHeight()/2;
            return new Pos(drawX, drawY);

        }

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
     * @param lengthRow the max amount of X
     * @param lengthColumn the max amount of Y
     * @return a {@link Hitbox} that is clamped to the bounds of the map
     */
    public Hitbox getRealCamera(int lengthRow, int lengthColumn) {

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
