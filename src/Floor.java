import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;


public class Floor{

	private RectangleShape shape=new RectangleShape();
	private float width=0;
	private float length=0;
	private double xPosition;
	private double yPosition;
	private boolean isblock=false;
	BiConsumer<Float, Float> setPosition;

	Floor(int x,int y,float w,float l,boolean isblock)
	{
		xPosition=x;
		yPosition=y;
		width=w;
		length=l;
		this.isblock=isblock;
		shape.setSize(new Vector2f(length,width));
		shape.setPosition(new Vector2f((float)xPosition,(float)yPosition));
		shape.setFillColor(Color.TRANSPARENT);    //Let the color be transparent
		//shape.setOrigin(new Vector2f(xPosition, yPosition));
	}


	double getXPosition()
	{
		Vector2f p=shape.getPosition();
		return p.x;
	}

	double getYPosition()
	{
		Vector2f p=shape.getPosition();
		return p.y;
	}

	float getWidth()
	{
		return width;
	}

	float getLength()
	{
		return length;
	}

	boolean isBlock()
	{
		return isblock;
	}

	/*public void setOrigin() {
        Vector2f pos = new Vector2f((float)xPosition, (float)yPosition);
        shape.setOrigin(pos);
    } */

	public void draw(RenderWindow pane)
	{
		pane.draw(shape);
	}
}
