package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Consumer;

public class FileHelper {

    /**
     * Reads a file & passes each line to the consumer
     * @param f the function that will handle the string
     * @param location the location of the file to be read
     */
    public void readAndConsume(Consumer<String> f, String location) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(location));

            while(reader.ready())
                f.accept(reader.readLine());

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

}
