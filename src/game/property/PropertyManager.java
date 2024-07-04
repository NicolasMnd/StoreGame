package game.property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PropertyManager {

    private Set<Property> properties;

    public PropertyManager() {
        this.properties = new HashSet<>();
    }

    /**
     * Adds a property to the property set
     * @param property the to be added {@link Property} instance
     */
    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public void removeProperty(PropertyType property) {
        for(int i = 0; i < properties.size(); i++)
            if(properties.stream().toList().get(i).getType().equals(property)) {
                List<Property> newSet = new ArrayList<>(properties.stream().toList());
                newSet.remove(i);
                properties = new HashSet<>(newSet);
            }

    }

    /**
     * Returns the {@link Property} that matches the {@link PropertyType}
     * @param type the enum type
     * @return the {@link Property} that matches the enum {@link PropertyType}
     */
    public Property getProperty(PropertyType type) {
        for(Property p : properties)
            if(p.getType().equals(type))
                return p;
        return null;
    }

}
