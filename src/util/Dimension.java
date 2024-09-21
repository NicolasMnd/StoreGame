package util;

public class Dimension {

    private final int width, height;

    public Dimension(int height, int width) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Dimension flip() {
        return new Dimension(width, height);
    }

    public String getFormat() {
        return "[l:" + height + ", c:" + width + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Dimension dim) {
            return dim.getHeight() == this.getHeight() && dim.getWidth() == this.getWidth();
        }
        return false;
    }
}
