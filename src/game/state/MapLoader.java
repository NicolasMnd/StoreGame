package game.state;

import game.map.MapHandler;

public class MapLoader extends StateObject {

    @Override
    public String setKey() {
        return "";
    }

    @Override
    public void load(GameState state) {

        MapHandler handler = new MapHandler("resources/map/" + state.mapName + ".csv", state.tileSize, state.gameSizeListener);
        state.tiles = handler.readMap();
        state.containers = handler.getContainers();

    }

    @Override
    public void save(GameState state) {

        // Todo saving container contents

    }

}
