import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;

public abstract class Asset {
	Drawable item;
	IntConsumer rotate;
	BiConsumer<Float, Float> setPosition;

	protected int health;
	protected boolean hit;
	private boolean livingObject = false;
	protected double xPosition=0;
	protected double yPosition=0;
	protected int xSpeed=0;
	protected int ySpeed=0;
	
	public void setLiving(boolean a) {
		this.livingObject = a;
	}
	
	public boolean getLiving() {
		return livingObject;
	}
	
	public void setXPosition(double i) {
		this.xPosition = i;
	}
	
	public void setYPosition(double i) {
		this.yPosition = i;
	}
	
	public void setXSpeed(int i) {
		this.xSpeed = i;
	}
	
	public void setYSpeed(int i) {
		this.ySpeed = i;
	}
	
	public void testItem(int minX, int minY, int maxX, int maxY) {
		xPosition += xSpeed;
		yPosition += ySpeed;
		if(xPosition<=minX || xPosition>=maxX) {
			xSpeed = -(xSpeed);
		}
		if(yPosition<=minY || yPosition>=maxY) {
			ySpeed = -(ySpeed);
		}
		setPosition.accept((float)xPosition, (float)yPosition);
	}
	
	public void draw(RenderWindow pane) {
		setPosition.accept((float)xPosition, (float)yPosition);
		pane.draw(item);
	}

	public int getHealth() {
		return health;
	}

	public boolean isHit() {
		return hit;
	}

	public void reduceHealth(int damage) {
		health = health - damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
