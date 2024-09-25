package render.game.renderorder;

public enum RenderStage {

    BACKGROUND(0), PLAYER_SHADOW(1), TILES(2), PLAYER_SUB(3), PLAYER(4), PLAYER_SUPER(5), FOREGROUND(6);

    private int stage;

    RenderStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return this.stage;
    }

}
