package game.property;

import game.GameObject;
import game.tile.GameTile;
import game.tile.TileShelf;
import util.positions.Pos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PropertyPeer extends Property {

    private final TileShelf parent;
    private Set<TileShelf> peers;

    public PropertyPeer(TileShelf shelf) {
        super(PropertyType.SHELF_PEER);
        this.parent = shelf;
        this.peers = new HashSet<>();
    }

    /**
     * Adds a peer to our set
     * @param shelf the {@link TileShelf} object to be added to our peers
     */
    public void addPeer(TileShelf shelf) {
        // Firstly adding a bidirectional association
        this.peers.add(shelf);
        if(!getProperty(shelf).containsPeer(this.parent))
            getProperty(shelf).addPeer(this.parent);

        // Secondly adding all peers of this to the peers of others
        for(TileShelf selectedPeer : peers)
            for(TileShelf otherPeer : peers)
                if(selectedPeer != otherPeer)
                    if(!getProperty(selectedPeer).containsPeer(otherPeer))
                        getProperty(selectedPeer).addPeer(otherPeer);

        this.peers.remove(parent);
    }

    /**
     * Removes a certain shelf from the set
     * @param shelf the shelf to be removed
     */
    public void removePeer(TileShelf shelf) {
        if(peers.contains(shelf))
            peers.remove(shelf);
    }

    /**
     * Determines if the parameter {@link GameTile} object is in this set of peers
     * @param shelf the tile that should be checked if it is part of the collection
     * @return a boolean determining its presence in the set
     */
    public boolean containsPeer(GameTile shelf) {
        return this.peers.contains(shelf);
    }

    /**
     * Returns the set of peers
     * @return set of peers
     */
    public Set<TileShelf> getPeers() {
        return this.peers;
    }

    /**
     * Determine if the peer is positioned between other peers
     * @return boolean determining if the parent shelf is the middle parent
     */
    public boolean isMiddle() {
        List<Pos> peerPos = this.peers.stream().map(GameObject::getPosition).toList();
        return peerPos.stream().anyMatch(pos ->
                pos.operation.isXOrYLower(parent.getPosition(), pos)
                )
                &&
                peerPos.stream().anyMatch(pos ->
                        pos.operation.isXOrYHigher(parent.getPosition(), pos)
                );
    }

    /**
     * @return a boolean determining if this property peer is most left
     */
    public boolean isLeftSide() {
        List<Pos> peerPos = this.peers.stream().map(GameObject::getPosition).toList();
        return peerPos.stream().allMatch(pos ->
                pos.operation.isXOrYHigher(parent.getPosition(), pos)
        );
    }

    public boolean isRightSide() {
        List<Pos> peerPos = this.peers.stream().map(GameObject::getPosition).toList();
        return peerPos.stream().allMatch(pos ->
                pos.operation.isXOrYLower(parent.getPosition(), pos)
        );
    }

    private PropertyPeer getProperty(TileShelf shelf) {
        return ((PropertyPeer) shelf.getProperties().getProperty(PropertyType.SHELF_PEER));
    }

}
