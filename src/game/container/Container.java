package game.container;

import game.item.GameItem;
import game.item.GameItemRegistry;
import listeners.IContainerInteraction;
import util.FileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Container {

    private final String codeContainer;
    private List<GameItem> items;

    public Container(String codeContainer) {
        this.codeContainer = codeContainer;
        this.items = new ArrayList<>();
        this.initializeContainer();
    }

    /**
     * The integer that represents the identity of the {@link Container}
     * @return the container code
     */
    public String getContainerCode() {
        return this.codeContainer;
    }

    /**
     * @return an interface that lets user interact with the {@link Container}
     */
    public IContainerInteraction getInteractor() {
        return new IContainerInteraction() {

            @Override
            public GameItem removeItem(String id) {
                return remove(id);
            }

            @Override
            public void addItem(GameItem item) {
                add(item);
            }

            @Override
            public GameItem[] getContainerItems() {
                return items.toArray(GameItem[]::new);
            }
        };
    }

    /**
     * Returns the array of items
     * @return the {@link GameItem}s in the {@link Container}
     */
    public List<GameItem> getItems() {
        return items;
    }

    /**
     * Reads data from save files
     */
    private void initializeContainer() {
        Consumer<String> stringConsumer = string -> {
            String[] split = string.split("/");
            if(split[0].equals(this.codeContainer)) {

                String[] itemArray = split[1].split(";");
                for(int i = 0; i < itemArray.length; i++) {

                    String[] specificItem = itemArray[i].split("/");
                    int amount = Integer.parseInt(specificItem[1]);
                    while(amount-- > 0)
                        this.items.add(getItemForId(specificItem[0]));

                }

            }
        };
        new FileHelper().readAndConsume(stringConsumer, "resources/container/containerregistry.txt");
    }

    /**
     * Get a {@link GameItem} for a given string id. Used for loading the {@link Container#initializeContainer()}
     * @param id the id read from a file
     * @return a {@link GameItem} matching the id
     */
    private GameItem getItemForId(String id) {
        return new GameItemRegistry().getItemForId(id);
    }

    /**
     * Called when a {@link GameItem} is selected and removed from the {@link Container#items} list
     * @param id the id that should be removed
     * @return a {@link GameItem} from the {@link Container#items}
     */
    private GameItem remove(String id) {
        GameItem item = this.items.stream().filter(check -> check.getId().equals(id)).toList().get(0);
        this.items.remove(item);
        return item;
    }

    /**
     * Adds an item to the {@link Container#items}
     * @param item the item to be added
     */
    private void add(GameItem item) {
        this.items.add(item);
    }

}
