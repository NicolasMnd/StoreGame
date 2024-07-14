package util.texture.textureinformation;

import game.entity.PlayerEntity;
import util.texture.comp.DirectedTexture;
import util.texture.comp.MultiTexture;
import util.texture.comp.TextureHolder;

import java.awt.image.BufferedImage;
import java.io.File;

import static util.Direction.*;

public class PlayerTextureInformation extends TextureInformation {

    private final PlayerEntity player;

    public PlayerTextureInformation(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public TextureHolder loadTexture() {
        DirectedTexture versionOne = new DirectedTexture();
        versionOne.addTexture(UP, new File("resources/entities/human_walk_up_1.png"));
        versionOne.addTexture(DOWN, new File("resources/entities/human_walk_down_1.png"));
        versionOne.addTexture(RIGHT, new File("resources/entities/human_walk_right_1.png"));
        versionOne.addTexture(LEFT, new File("resources/entities/human_walk_left_1.png"));

        DirectedTexture versionTwo = new DirectedTexture();
        versionTwo.addTexture(UP, new File("resources/entities/human_walk_up_2.png"));
        versionTwo.addTexture(DOWN, new File("resources/entities/human_walk_down_2.png"));
        versionTwo.addTexture(RIGHT, new File("resources/entities/human_walk_right_2.png"));
        versionTwo.addTexture(LEFT, new File("resources/entities/human_walk_left_2.png"));

        DirectedTexture idle = new DirectedTexture();
        idle.addTexture(UP, new File("resources/entities/human_stand_up.png"));
        idle.addTexture(DOWN, new File("resources/entities/human_stand_down.png"));
        idle.addTexture(RIGHT, new File("resources/entities/human_stand_right.png"));
        idle.addTexture(LEFT, new File("resources/entities/human_stand_left.png"));

        MultiTexture versions = new MultiTexture();
        versions.addTexture("version1_walk", versionOne);
        versions.addTexture("version2_walk", versionTwo);
        versions.addTexture("idle", idle);

        return versions;
    }

    @Override
    public BufferedImage retrieveTexture() {

        MultiTexture versions = (MultiTexture) player.getTexture();
        DirectedTexture directedTexture = getImageFor(versions);

        return directedTexture.getTexture(player.getFacing()).getImage();
    }

    /**
     * @return the subtexture for idling or walking
     */
    private DirectedTexture getImageFor(MultiTexture multi) {
        if(player.isWalking())
            return (DirectedTexture) multi.getTexture("version" + player.getWalkVersion() + "_walk");
        else
            return (DirectedTexture) multi.getTexture("idle");
    }


}
