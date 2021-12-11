import org.jsfml.graphics.*;
import org.jsfml.system.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Write extends Asset{
	private Text text = new Text();
	private Font font = new Font();
	public Write(float x, float y, String type, int size, Color c)  {
		Vector2f pos = new Vector2f(x,y);
		try {
			font.loadFromFile(Paths.get("src/font.ttf"));
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

	public void incSize() {
		text.setCharacterSize(text.getCharacterSize()+1);
	}

	public int getSize() {
		return text.getCharacterSize();
	}
}