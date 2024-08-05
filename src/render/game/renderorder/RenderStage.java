package render.game.renderorder;

public enum RenderStage {

    BACKGROUND(0), PLAYER_UNDER(1), TILES(2), PLAYER(3), FOREGROUND(4);

    private int stage;

    RenderStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return this.stage;
    }

}
