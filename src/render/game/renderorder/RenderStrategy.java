package render.game.renderorder;

import game.GameObject;
import game.entity.PlayerEntity;
import game.ScreenObject;
import util.positions.Hitbox;
import util.positions.Pos;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.IRender;

import java.awt.*;

public class RenderStrategy {

    public IRender imageRenderer(GameObject object) {
        return (graphics, gameSize, tileSize) -> {
            Pos drawPosition = object.getPosition();
            drawPosition.addY(-object.getHeight() + tileSize);
            graphics.drawImage(
                    object.textureSelector(new TextureSelector()).retrieveTexture(),
                    (int) (drawPosition.x()),
                    (int) (drawPosition.y()),
                    (int) (object.getWidth()*gameSize),
                    (int) (object.getHeight()*gameSize),
                    null
            );
        };
    }

    public IRender rectangleRenderer(GameObject object) {
        return (graphics, gameSize, tileSize) -> {
            graphics.setStroke(new BasicStroke(5));
            Pos drawPosition = object.getPosition();

            Rectangle rect = new Rectangle(
                    (int) (object.getPosition().x()),
                    (int) (drawPosition.y()),
                    (int) (object.getWidth()*gameSize),
                    (int) (object.getHeight()*gameSize)
            );
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(
                    (int) (object.getPosition().x()),
                    (int) (drawPosition.y()),
                    (int) (object.getWidth()*gameSize),
                    (int) (object.getHeight()*gameSize)
            );


            graphics.draw(rect);
        };
    }

    public IRender emptyRectangleRenderer(GameObject object) {
        return (graphics, gameSize, tileSize) -> {
            graphics.setStroke(new BasicStroke(1));
            Pos drawPosition = object.getPosition();
            graphics.setColor(Color.RED);
            graphics.drawRect(
                    (int) (object.getPosition().x()),
                    (int) (object.getPosition().y()),
                    (int) (object.getWidth()*gameSize),
                    (int) (object.getHeight()*gameSize)
            );

        };
    }

    public IRender hitboxRenderer(GameObject object) {
        return (graphics, gameSize, tileSize) -> {
            this.imageRenderer(object).render(graphics, gameSize, tileSize);

            Pos offsetRenderScreen = object.getPosition();
            GameObject parent = ((RenderableGameObject) object).parent();

            Pos start = object.getHitbox().getUpperleft().subtract(parent.getPosition()).add(offsetRenderScreen);
            Pos end = object.getHitbox().getLowerright().subtract(parent.getPosition()).add(offsetRenderScreen);
            int x = start.x();
            int y = start.y();
            int width = end.x() - x;
            int height = end.y() - y;

            Stroke stroke1 = new BasicStroke(1f);
            graphics.setColor(Color.RED);
            graphics.setStroke(stroke1);
            graphics.drawRect(x, y, width, height);

        };
    }

    public IRender renderHitbox(Hitbox hitbox) {
        return (graphics, gameSize, tileSize) -> {
            Pos start = hitbox.getUpperleft();
            Pos end = hitbox.getLowerright();
            int x = start.x();
            int y = start.y();
            int width = end.x() - x;
            int height = end.y() - y;

            Stroke stroke1 = new BasicStroke(1f);
            graphics.setColor(Color.RED);
            graphics.setStroke(stroke1);
            graphics.drawRect(x, y, width, height);

        };
    }

    public IRender doubleImage(ScreenObject first, ScreenObject second) {
        return (graphics, gameSize, tileSize) -> {
            Pos firstPosition = first.getPosition().clone();
            Pos drawPosition = first.getPosition();
            drawPosition.addY(-first.getHeight() + tileSize);
            graphics.drawImage(
                    second.textureSelector(new TextureSelector()).retrieveTexture(),
                    (int) (firstPosition.x()),
                    (int) (firstPosition.y()),
                    (int) (second.getWidth()*gameSize),
                    (int) (second.getHeight()*gameSize),
                    null
            );
            graphics.drawImage(
                    first.textureSelector(new TextureSelector()).retrieveTexture(),
                    (int) (drawPosition.x()),
                    (int) (drawPosition.y()),
                    (int) (first.getWidth()*gameSize),
                    (int) (first.getHeight()*gameSize),
                    null
            );
        };
    }

    public IRender renderCoordinates(PlayerEntity player) {
        return (graphics, gameSize, tileSize) -> {
            graphics.setStroke(new BasicStroke(1f));
            graphics.setColor(Color.RED);
            graphics.drawString(player.getPosition().getFormat(), 10, 10);
            graphics.drawString(player.getHitbox().getPrint(), 10, 20);
        };
    }

    public IRender imageRenderer(ScreenObject object) {
        return (graphics, gameSize, tileSize) -> {
            Pos drawPosition = object.getPosition();
            drawPosition.addY(-object.getHeight() + tileSize);
            graphics.drawImage(
                    object.textureSelector(new TextureSelector()).retrieveTexture(),
                    (int) (drawPosition.x()),
                    (int) (drawPosition.y()),
                    (int) (object.getWidth()*gameSize),
                    (int) (object.getHeight()*gameSize),
                    null
            );
        };
    }

}
