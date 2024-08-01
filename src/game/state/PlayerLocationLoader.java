package game.state;

import game.entity.PlayerEntity;
import util.FileHelper;
import util.positions.Pos;

public class PlayerLocationLoader extends StateSave implements GameInfo {

    @Override
    public String setKey() {
        return "playerPosition";
    }

    @Override
    public void load(GameState state) {

        int[] positionPlayer = new int[] {0, 0};
        new FileHelper().readAndConsume(
                (string) -> {
                    if(string.split("=")[0].equalsIgnoreCase(key)) {
                        positionPlayer[0] = Integer.parseInt(string.split("=")[1].split(",")[0]);
                        positionPlayer[1] = Integer.parseInt(string.split("=")[1].split(",")[1]);
                    }
                },
                this.saveFileLocation.getAbsolutePath()
        );

        state.player = new PlayerEntity(new Pos(positionPlayer[0], positionPlayer[1]), state.getMoveChecker());

    }

    @Override
    public void save(GameState state) {
        String positionString = state.getPlayer().getPosition().x() + "," + state.getPlayer().getPosition().y();
        this.replaceEntry(positionString);
    }

}
