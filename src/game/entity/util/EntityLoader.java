package game.entity.util;

import game.entity.types.Entity;
import game.entity.types.PlayerEntity;
import listeners.IMoveValidity;
import util.positions.Pos;
import util.texture.comp.TextureHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityLoader {

    /**
     * The map of {@link TextureHolder}s for each type of {@link Entity}
     */
    private Map<String, TextureHolder> textureRegistry;
    private final IMoveValidity positionValidityListener;

    public EntityLoader(IMoveValidity positionValidityListener) {
        this.positionValidityListener = positionValidityListener;
    }

    public List<Entity> loadEntities() {
        List<Entity> list = new ArrayList<>();

        return list;
    }

    public Entity addEntity(EntityType type, Pos spawnPosition) {
        switch(type) {
            case PLAYER:
                return new PlayerEntity(spawnPosition, this.positionValidityListener, null);
            case CUSTOMER:
                break;
        }
        return null;
    }

    public enum EntityType {
        PLAYER, CUSTOMER
    }

}
