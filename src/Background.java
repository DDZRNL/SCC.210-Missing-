import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import java.nio.file.Paths;

public class Background extends Asset {

    private Sprite background;

    public Background(String filePath) {
        Texture texture = new Texture();
        try {
            texture.loadFromFile(Paths.get(filePath));
        } catch(Exception e) {
            e.printStackTrace();
        }

        background = new Sprite();
        background.setTexture(texture);


        background.setOrigin(Vector2f.div(new Vector2f(texture.getSize()), 2));

        item = background;
        setPosition = background::setPosition;
    }

    public void setOrigin(int x, int y) {
        Vector2f pos = new Vector2f(x, y);
        background.setOrigin(pos);
    }

}
