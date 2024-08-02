package game.state;

public class PlayerWorldLoader extends StateObject {

    @Override
    public String setKey() {
        return "playerWorld";
    }

    @Override
    public void load(GameState state) {
        state.mapName = getEntry();
    }

    @Override
    public void save(GameState state) {
        replaceEntry(state.mapName);
    }

}
