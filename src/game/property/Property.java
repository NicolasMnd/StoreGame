package game.property;

/**
 * The property is supposed to work for a specific type of class. This way we can specifically call
 * upon methods of that type.
 */
public abstract class Property {

    private final PropertyType type;

    public Property(PropertyType type) {
        this.type = type;
    }

    /**
     * Returns the type of this {@link Property}
     * @return the enum {@link PropertyType}
     */
    public PropertyType getType() {
        return this.type;
    }

}
