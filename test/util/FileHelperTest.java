package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileHelperTest {

    @TempDir
    Path path1;

    @BeforeEach
    public void init() throws IOException {
        path1 = path1.resolve("test.txt");
        Files.write(path1, "hello".getBytes());
        Consumer<String> test = (str) -> System.out.println();
    }

    @Test
    public void testRead_Exception() {
        Consumer<String> test = (str) -> System.out.println();
        assertThrows(Exception.class, () -> new FileHelper().readAndConsume(test, "imaginaryLocation.txt"));
    }

    @AfterEach
    @EnabledOnOs(OS.WINDOWS)
    void cleanUp() {
        System.gc();
    }


}
