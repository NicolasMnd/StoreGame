package game.tile;

import game.GameObject;
import util.texture.Texture;
import util.Pos;

import java.util.ArrayList;
import java.util.List;

public abstract class GameTile extends GameObject {

    /**
     * The list of peers of this object
     */
    private List<GameTile> peers = new ArrayList<>();

    public GameTile(Pos pos, Texture texture) {
        super(pos, texture);
    }

    @Override
    public void updatePosition(Pos pos) {
        return;
    }

    /**
     * Allows this {@link GameTile} to have a relation with another tile
     * @param peer the tile you want to connect with this one.
     */
    public void addPeer(GameTile peer) {
        this.peers.add(peer);
    }

    /**
     * Returns the list of {@link GameTile} consisting of peers to this object
     * @return
     */
    public List<GameTile> getPeers() {
        return this.peers;
    }

}
