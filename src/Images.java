import org.jsfml.graphics.*;
import org.jsfml.system.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Images 
{
	private Sprite sprite = new Sprite();
	private Texture texture  = new Texture();
	public Images(float x, float y, float a, float b, String file) 
	{
		Vector2f position = new Vector2f(x,y);
		Vector2f size = new Vector2f(a,b);
		try {
			texture.loadFromFile(Paths.get(file));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		sprite.setTexture(texture);
		sprite.setScale(size);
		sprite.setPosition(position);
	}

	public void draw(RenderWindow w){
		w.draw(sprite);
	}
}