package game.state;

import game.entity.PlayerEntity;
import util.positions.Pos;

public class PlayerLocationLoader extends StateObject {

    @Override
    public String setKey() {
        return "playerLocation";
    }

    @Override
    public void load(GameState state) {
        int x = Integer.parseInt(getEntry().split(",")[0]);
        int y = Integer.parseInt(getEntry().split(",")[1]);
        state.player = new PlayerEntity(new Pos(x, y), state.getMoveChecker());
    }

    @Override
    public void save(GameState state) {
        String positionString = state.getPlayer().getPosition().x() + "," + state.getPlayer().getPosition().y();
        this.replaceEntry(positionString);
    }

}
