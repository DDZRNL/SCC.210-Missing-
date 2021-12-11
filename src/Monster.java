import java.nio.file.Paths;
import java.util.ArrayList;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Monster extends Asset{
	protected Sprite mob;
	protected IntRect frame;
	protected ArrayList<Asset> list;
	protected int frameWidth;
	private int frameHeight;

	public void mirrorSprite() {
		frameWidth = -(frameWidth);
	}

	public void updateSprite(int frameX, int frameY) {
		frame = new IntRect(frameX,frameY,frameWidth,frameHeight);
		mob.setTextureRect(frame);
	}

	public void updateImg(String textureFile) {
		Texture img = new Texture();
		try {
			img.loadFromFile(Paths.get(textureFile));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		frame = new IntRect(0,0,frameWidth,frameHeight);
		mob = new Sprite(img,frame);
		mob.setOrigin(Vector2f.div(new Vector2f(img.getSize()),2));
		item = mob;
		setPosition = mob::setPosition;
	}

	public Monster(int width, int height,String textureFile, int health, ArrayList<Asset> list) {
		this.health = health;
		this.frameWidth = width;
		this.frameHeight = height;
		Texture img = new Texture();
		try {
			img.loadFromFile(Paths.get(textureFile));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		frame = new IntRect(0,0,frameWidth,frameHeight);
		mob = new Sprite(img,frame);
		mob.setOrigin(Vector2f.div(new Vector2f(img.getSize()),2));
		item = mob;
		setPosition = mob::setPosition;
		this.list = list;
		this.list.add(this);
	}
}
