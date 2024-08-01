package game.state;

import game.map.MapHandler;

public class MapLoader extends StateSave {

    @Override
    public String setKey() {
        return "";
    }

    @Override
    public void load(GameState state) {

        MapHandler handler = new MapHandler(state.mapName, state.tileSize, state.gameSizeListener);
        state.mapHandler = handler;
        state.tiles = handler.readMap();
        state.containers = handler.getContainers();

    }

    @Override
    public void save(GameState state) {

        // Todo saving container contents

    }

}
