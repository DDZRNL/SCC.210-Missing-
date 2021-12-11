import java.nio.file.Paths;
import java.util.ArrayList;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Object extends Asset{
	protected Sprite mob;
	protected IntRect frame;
	protected ArrayList<Asset> list;
	protected int frameWidth=48;
	private int frameHeight=48;
	private String textureFile;
	private int type=0;

	public void mirrorSprite() {
		frameWidth = -(frameWidth);
	}

	public void updateSprite(int frameX, int frameY) {
		frame = new IntRect(frameX,frameY,frameWidth,frameHeight);
		mob.setTextureRect(frame);
	}
	
	public void HealthPotion() {
		textureFile="src/Potion/Health.png";
	}
	
	public void JumpPotion() {
		textureFile="src/Potion/Jump.png";
	}
	
	public void AttackPotion() {
		textureFile="src/Potion/Damage.png";
	}
	
	public void SpeedPotion() {
		textureFile="src/Potion/Speed.png";
	}
	
	public void TypeDecide()
	{
		if(type==1) {
			HealthPotion();
		}
		else if(type==2) {
			JumpPotion();
		}
		else if(type==3) {
			AttackPotion();
		}
		else if(type==4) {
			SpeedPotion();
		}
	}


	public Object(int x,int y, ArrayList<Asset> list,int type) {
		this.type=type;
		setXPosition(x);
		setYPosition(y);
		TypeDecide();
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


