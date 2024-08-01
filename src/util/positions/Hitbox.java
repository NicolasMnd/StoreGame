package util.positions;

public class Hitbox {

    private final Pos start, end;
    private int height;

    public Hitbox(Pos pos1, Pos pos2) {
        this.start = pos1;
        this.end = pos2;
    }

    public Hitbox(Pos center, int sizeX, int sizeY) {
        this.start = center.add(new Pos(-sizeX, -sizeY));
        this.end = center.add(new Pos(sizeX, sizeY));
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
                || box.isInHitbox(this.getLowerLeft()) || box.isInHitbox(this.getLowerright()) || box.isInHitbox(this.getUpperRight()) || box.isInHitbox(this.getUpperleft())
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

    /**
     * Calculates a shifted hit box given an upper left position
     * @param upperLeft the new upper left position of the hit box
     * @return a shifted hit box
     */
    public Hitbox calculateRelativeHitbox(Pos upperLeft) {
        int dX = getLowerright().x() - getUpperleft().x();
        int dY = getLowerright().y() - getUpperleft().y();
        return new Hitbox(upperLeft, new Pos(upperLeft.x() + dX, upperLeft.y() + dY));
    }

    /**
     * Sets the height on a z axis for this hitbox
     * @param height the z axis height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Hitbox b)
            return b.getCenterPos().equals(this.getCenterPos());
        return false;
    }
}
