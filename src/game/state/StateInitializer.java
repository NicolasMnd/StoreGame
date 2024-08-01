package game.state;

import java.util.List;

public class StateInitializer {

    /**
     * The list of {@link GameInfo} objects which can load and save data.
     */
    private final List<GameInfo> gameInfoList;

    public StateInitializer() {
        this.gameInfoList = List.of(
                new PlayerLocationLoader()
        );
    }

    /**
     * Saves the current game state to disk
     * @param state the current {@link GameState}
     */
    public void save(GameState state) {
        for(GameInfo i : gameInfoList)
            i.save(state);
    }

    /**
     * Loads the current game state to program
     * @param state the {@link GameState} which retrieves information
     */
    public void load(GameState state) {
        for(GameInfo i : gameInfoList)
            i.load(state);
    }

}
