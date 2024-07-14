package render;

import game.GameObject;
import util.Pos;
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

            Pos start = object.getHitbox().getUpperleft();
            int x = start.x();
            int y = start.y();
            int width = object.getHitbox().getLowerright().x() - object.getHitbox().getUpperleft().x();
            int height = object.getHitbox().getLowerright().y() - object.getHitbox().getUpperleft().y();

            Stroke stroke1 = new BasicStroke(2f);
            graphics.setColor(Color.RED);
            graphics.setStroke(stroke1);
            graphics.drawRect(x, y, width, height);

        };
    }

}
