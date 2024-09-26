package game.entity.util;

import game.entity.LimbTracker;
import game.entity.PlayerEntity;
import util.positions.Pos;

public class LimbVisitor {

    public LimbTracker getLimbTracker(PlayerEntity player) {
        return new LimbTracker(player) {
            @Override
            public Pos getLeftHand() {
                if(player.isWalking()) {
                    switch (player.getFacing()) {
                        case UP:
                            return player.getWalkVersion() == 1 ? new Pos(2, 8) : new Pos(2, 9);
                        case DOWN:
                            return new Pos(20, 9); // beide 12,11
                        case RIGHT:
                            return player.getWalkVersion() == 1 ? new Pos(7, 9) : new Pos(14, 8);
                        case LEFT:
                            return player.getWalkVersion() == 1 ? new Pos(7, 9) : new Pos(14, 8);
                    }
                }
                else {
                    switch (player.getFacing()) {
                        case UP:
                            return new Pos(2, 9);
                        case DOWN:
                            return new Pos(20, 7);
                        case RIGHT:
                            return new Pos(7, 9);
                        case LEFT:
                            return new Pos(12, 12);
                    }
                }
                System.out.println("Facing: " + player.getFacing());
                return null;
            }

            @Override
            public Pos getRightHand() {
                if(player.isWalking()) {
                    switch (player.getFacing()) {
                        case UP:
                            return player.getWalkVersion() == 1 ? new Pos(11, 11) : new Pos(13, 11);
                        case DOWN:
                            return new Pos(2, 9); // beide 3,11
                        case RIGHT:
                            return player.getWalkVersion() == 1 ? new Pos(4,10) : new Pos(8, 8);
                        case LEFT:
                            return player.getWalkVersion() == 1 ? new Pos(6, 8) : new Pos(4, 9);
                    }
                }
                else {
                    switch (player.getFacing()) {
                        case UP:
                            return new Pos(12, 11);
                        case DOWN:
                            return new Pos(3, 11);
                        case RIGHT:
                            return new Pos(6, 11);
                        case LEFT:
                            return new Pos(9, 9);
                    }
                }
                return null;
            }
        };
    }

}
