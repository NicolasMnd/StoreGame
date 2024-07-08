package util.texture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import org.w3c.dom.Text;
import util.Direction;
import util.texture.comp.DirectedTexture;
import util.texture.comp.MultiTexture;
import util.texture.comp.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TextureCompositeTest {

    @TempDir
    Path path, path2;

    BufferedImage image, image2;

    @BeforeEach
    public void init() throws IOException {
        image = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
        image2 = new BufferedImage(10, 11, BufferedImage.TYPE_3BYTE_BGR);
        path.resolve("image_location.png");
        ImageIO.write(image, "png",new File(path.toString()));

        path2.resolve("image_location2.png");
        ImageIO.write(image2, "png",new File(path2.toString()));
    }

    @Test
    public void testTextureInstance_NoError() {
        Texture t = new Texture(path.toFile());
    }

    @Test
    public void testTextureInstance_NoImage() {
        assertNull(new Texture(new File("hello mister.png")).getImage());
    }

    @Test
    public void testTextureInstance_Equals() {
        assertNotEquals(new Texture(path.toFile()), "hello");
        assertNotEquals(new Texture(path.toFile()), new Texture(path2.toFile()));
        assertEquals(new Texture(path.toFile()), new Texture(path.toFile()));
    }

    @Test
    public void testDirectedTexture_AddTexturesAndRetrieve() {
        DirectedTexture directedTexture = new DirectedTexture();
        directedTexture.addTexture(Direction.NORTH, new Texture(path.toFile()));
        assertEquals(directedTexture.getTexture(Direction.NORTH), new Texture(path.toFile()));
    }

    @Test
    public void testDirectedTexture_RetrieveNonSpecifiedDirection() {
        DirectedTexture directedTexture = new DirectedTexture();
        directedTexture.addTexture(Direction.SOUTH, new Texture(path.toFile()));
        assertNull(directedTexture.getTexture(Direction.NORTH));
        assertNotEquals(directedTexture.getTexture(Direction.NORTH), new Texture(path.toFile()));
    }

    @Test
    public void testMultiTexture_AddTexturesAndRetrieve_NonDirected() {
        MultiTexture multi = new MultiTexture();
        multi.addTexture("left", new Texture(path.toFile()));
        DirectedTexture dir = new DirectedTexture();
        dir.addTexture(Direction.SOUTH, new Texture(path2.toFile()));
        multi.addTexture("right", dir);

        assertNull(multi.getTexture("mister"));
        assertInstanceOf(Texture.class, multi.getTexture("left"));
        assertEquals(new Texture(path.toFile()), multi.getTexture("left"));

        assertInstanceOf(DirectedTexture.class, multi.getTexture("right"));
        assertEquals(dir, multi.getTexture("right"));
    }

    @AfterEach
    @EnabledOnOs(OS.WINDOWS)
    void cleanUp() {
        System.gc();
    }


}
