package game.state;

import com.misterf.core.MisterF;

import java.io.File;

/**
 * Generically, data involving the {@link GameState} will be saved in a specific file. Furthermore, saving involves replacing entries, therefore {@link MisterF} will be used.
 */
public abstract class StateSave implements GameInfo {

    protected final File saveFileLocation;
    protected final String key;

    public StateSave() {
        this.saveFileLocation = new File("resources/state.txt");
        this.key = setKey();
    }

    public abstract String setKey();

    /**
     * Uses {@link MisterF} to replace an entry with a new value
     * @param replacement the replacement string for entry
     */
    public void replaceEntry(String replacement) {
        new MisterF(saveFileLocation.getAbsolutePath()).replaceEntry("=", key, replacement);
    }

    /**
     * @return the value for a given key
     */
    public String getEntry() {
        return new MisterF(saveFileLocation.getAbsolutePath()).getValue("=", key);
    }

}
