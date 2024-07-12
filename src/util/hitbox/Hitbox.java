package util.hitbox;

import util.Pos;

import java.awt.*;

public class Hitbox {

    private final Pos start, end;

    public Hitbox(Pos pos1, Pos pos2) {
        this.start = pos1;
        this.end = pos2;
    }

    public boolean isInHitbox(Pos pos) {
        return pos.x() <= end.x() && pos.x() >= start.x() && pos.y() <= end.y() && pos.y() >= start.y();
    }

    public Pos getCenterPos() {
        int addToEndX = ((start.x() - end.x()) / 2);
        int addToEndY = ((start.y() - end.y()) / 2);
        return new Pos(
                end.x() + addToEndX,
                end.y() + addToEndY
        );
    }

    /*
    public void drawHitbox(Graphics2D graphics, Color color) {

        int x = this.start.x();
        int y = this.start.y();
        int width = getLowerright().x() - getUpperleft().x();
        int height = getLowerright().y() - getUpperleft().y();

        Stroke stroke1 = new BasicStroke(1f);

        graphics.setColor(color);
        graphics.setStroke(stroke1);
        graphics.drawRect(x, y, width, height);

    }*/

    public Pos getUpperleft() {
        return this.start;
    }

    public Pos getLowerright() {
        return this.end;
    }

    public int getWidth() {
        return Math.abs(end.x() - start.x());
    }

    public int getHeight() {
        return Math.abs(end.y() - start.y());
    }

    public boolean hasOverlap(Hitbox box) {
        int ax = start.x();
        int ay = start.y();
        int bx = end.x();
        int by = end.y();

        int cx = box.getUpperleft().x();
        int cy = box.getUpperleft().y();
        int dx = box.getLowerright().x();
        int dy = box.getLowerright().y();

        return isInHitbox(box.getUpperleft()) || isInHitbox(box.getLowerright()) || isInHitbox(box.getUpperRight()) || isInHitbox(box.getLowerLeft())
                //|| box.isInHitbox(this.getLowerLeft()) || box.isInHitbox(this.getLowerright()) || box.isInHitbox(this.getUpperRight()) || box.isInHitbox(this.getUpperleft())
                || (cy <= by && by <= dy && ax <= dx && cx <= bx)
                || (cy <= by && by <= dy && cy <= ay && ay <= dy && ax <= cx && dx <= bx)
                || (cy <= ay && ay <= dy && ax <= dx && cy <= by && cx <= bx);
    }

    public Pos getUpperRight() {
        return new Pos(getLowerright().x(), getUpperleft().y());
    }

    public Pos getLowerLeft() {
        return new Pos(getUpperleft().x(), getLowerright().y());
    }

    public String getPrint() {
        return "[" + start.x() + ", " + start.y() + "]:[" + end.x() + ", " + end.y() + "]";
    }

}
