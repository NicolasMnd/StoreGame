package util.texture.textureinformation;

import game.item.GameItem;
import game.map.MapHandler;
import game.map.TileReader;
import game.property.PropertyPeer;
import game.property.PropertyType;
import game.tile.GameTile;
import game.tile.TileShelf;
import listeners.IContainerInteraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import util.ImageHelper;
import util.Pos;
import util.texture.TextureSelector;
import util.texture.comp.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TestShelfInformation {

    @TempDir
    Path pa, pa2, im1, im2;

    MapHandler reader;
    GameTile[][] tiles, tiles2;

    @BeforeEach
    public void init() throws IOException {
        pa = pa.resolve("testmap.txt");
        Files.write(pa, ("shelf_or:n_cc:aa;shelf_or:n_cc:aa;shelf_or:n_cc:aa;shelf_or:n_cc:aa\n" +
                         "shelf_or:s_cc:ab;shelf_or:s_cc:ab;shelf_or:s_cc:ab;shelf_or:s_cc:ab").getBytes());
        pa2 = pa2.resolve("testmap.txt");
        Files.write(pa2, ("""
                shelf_or:w_cc:ab;shelf_or:n_cc:aa;shelf_or:n_cc:aa;shelf_or:n_cc:aa;shelf_or:n_cc:aa;shelf_or:e_cc:ab
                shelf_or:w_cc:ab;                ;                ;                ;                ;shelf_or:e_cc:ab
                shelf_or:w_cc:ab;                ;                ;                ;                ;
                shelf_or:w_cc:ab;                ;                ;                ;                ;
                """).getBytes());
        reader = new MapHandler(pa.toString());
        tiles = reader.readMap();

        reader = new MapHandler(pa2.toString());
        tiles2 = reader.readMap();
    }

    @Test
    public void testShelvesUseFlyweight() {
        TileShelf shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7, shelf8;
        shelf1 = (TileShelf) tiles[0][0];
        shelf2 = (TileShelf) tiles[0][1];
        shelf3 = (TileShelf) tiles[0][2];
        shelf4 = (TileShelf) tiles[0][3];
        shelf5 = (TileShelf) tiles[1][0];
        shelf6 = (TileShelf) tiles[1][1];
        shelf7 = (TileShelf) tiles[1][2];
        shelf8 = (TileShelf) tiles[1][3];
        assertTrue(shelf1.getTexture() == shelf2.getTexture() && shelf2.getTexture() == shelf3.getTexture() && shelf3.getTexture() == shelf4.getTexture());
        assertTrue(shelf1.getTexture() == shelf5.getTexture() && shelf5.getTexture() == shelf6.getTexture() && shelf6.getTexture() == shelf7.getTexture() && shelf7.getTexture() == shelf8.getTexture());
    }

    @Test
    public void testGetTexture_LeftNorth() throws IOException {
        assertTrue(
                imagesCompare(tiles[0][0].textureSelector(new TextureSelector()).retrieveTexture(),
                              new Texture(new File("resources/tiles/shelf_n_left.png")).getImage()
                        )
        );
    }

    @Test
    public void testGetTexture_MidNorth_First() throws IOException {
        assertTrue(
                imagesCompare(
                        tiles[0][1].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_n_mid.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_MidNorth_Second() throws IOException {
        assertTrue(
                imagesCompare(
                        tiles[0][2].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_n_mid.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_RightNorth() throws IOException {
        assertTrue(
                imagesCompare(tiles[0][3].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_n_right.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_LeftSouth() throws IOException {
        assertTrue(
                imagesCompare(tiles[1][0].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_s_left.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_MidSouth_1() throws IOException {
        assertTrue(
                imagesCompare(tiles[1][1].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_s_mid.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_MidSouth_2() throws IOException {
        assertTrue(
                imagesCompare(tiles[1][2].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_s_mid.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_RightSouth() throws IOException {
        assertTrue(
                imagesCompare(tiles[1][3].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_s_right.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_LeftWest() throws IOException {
        assertTrue(
                imagesCompare(tiles2[0][0].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_e_left.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_MidWest1() throws IOException {
        assertTrue(
                imagesCompare(tiles2[1][0].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_e_mid.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_MidWest2() throws IOException {
        assertTrue(
                imagesCompare(tiles2[2][0].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_e_mid.png")).getImage()
                )
        );
    }

    @Test
    public void testGetTexture_RightWest() throws IOException {
        assertTrue(
                imagesCompare(tiles2[3][0].textureSelector(new TextureSelector()).retrieveTexture(),
                        new Texture(new File("resources/tiles/shelf_e_right.png")).getImage()
                )
        );
    }

    private IContainerInteraction interactor() {
        return new IContainerInteraction() {
            @Override
            public GameItem removeItem(String id) {
                return null;
            }

            @Override
            public void addItem(GameItem item) {

            }

            @Override
            public GameItem[] getContainerItems() {
                return new GameItem[0];
            }
        };
    }

    private boolean imagesCompare(BufferedImage retrievedImage, BufferedImage correctImage) throws IOException {
        im1 = im1.resolve("im1.png");
        ImageIO.write(retrievedImage, "png", new File(im1.toString()));
        im2 = im2.resolve("im2.png");
        ImageIO.write(correctImage, "png", new File(im2.toString()));
        return ImageHelper.visuallyCompare(im1.toFile(), im2.toFile());
    }

    private PropertyPeer getProperty(TileShelf shelf) {
        return ((PropertyPeer) shelf.getProperties().getProperty(PropertyType.SHELF_PEER));
    }

    @AfterEach
    @EnabledOnOs(OS.WINDOWS)
    void cleanUp() {
        System.gc();
    }

}
