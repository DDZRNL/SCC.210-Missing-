import org.jsfml.graphics.*;
import org.jsfml.system.*;
import java.io.IOException;
import java.nio.file.Paths;

public class MenuButtons {
    private Text text = new Text();
    private Font font = new Font();

    public MenuButtons(float x, float y, String type, int size, Color c) {
        Vector2f pos = new Vector2f(x, y);
        try {
            font.loadFromFile(Paths.get("src/fontmenu.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setFont(font);
        text.setString(type);
        text.setColor(c);
        text.setCharacterSize(size);
        text.setPosition(pos);
    }

    public void draw(RenderWindow w) {
        w.draw(text);
    }
}