package game.property;

public class PropertyTickable extends Property {

    private final Runnable function;

    public PropertyTickable(Runnable runnable) {
        super(PropertyType.TICKABLE);
        this.function = runnable;
    }

    public void execute() {
        this.function.run();
    }

}
