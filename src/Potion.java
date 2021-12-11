import org.jsfml.window.*;
import org.jsfml.window.event.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;


public class Potion extends Asset{

	private Texture img = new Texture();
	private String textureFile;
	//private int PotionNum=0;
	private int type=0;
	protected Sprite mob;
	protected IntRect frame;
	protected ArrayList<Asset> list;
	protected int frameWidth=30;
	private int frameHeight=30;
	private RectangleShape shape=new RectangleShape();
	private float width=30;
	private float length=30;
	private double x;
	private double y;
	private double imgxPosition;    //imgxPosition=xPosition+....
	private double imgyPosition;    //imgyPosition=yPosition+....
	private boolean Haveused=false;
	private boolean duePast=false;
	private RenderWindow pane=null;
	
	public Potion(double x,double y, ArrayList<Asset> list,int type, RenderWindow pane) {
		this.pane=pane;
		this.list=list;
		this.type=type;
		
		this.x=x;
		this.y=y;
		shape.setSize(new Vector2f(length,width));
		shape.setPosition(new Vector2f((float)x,(float)y));
		shape.setFillColor(Color.TRANSPARENT);    //Let the color be transparent
		
		//imgxPosition=x;//-120;
		//imgyPosition=y;//-120;
		xPosition=x;
		yPosition=y;
		setXPosition(xPosition);
		setYPosition(yPosition);
		
		TypeDecide();
		drawPotion();
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
	
	public void drawPotion() {
		try {
			//img.create(30, 30);
			img.loadFromFile(Paths.get(textureFile));
			//img.setSize(48, 48);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		frame = new IntRect(0,0,frameWidth,frameHeight);
		mob = new Sprite(img,frame);
		mob.setOrigin(Vector2f.div(new Vector2f(img.getSize()),2));
		item = mob;
		setPosition = mob::setPosition;
		//mob.draw(pane, null);
		this.list.add(this);
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

	/*public void setOrigin() {
        Vector2f pos = new Vector2f((float)xPosition, (float)yPosition);
        shape.setOrigin(pos);
    } */
	

	public void draw(RenderWindow pane)
	{
		pane.draw(shape);
	}
	
	
	public int getType() {
		return type;
	}
	
	public boolean getStatus()
	{
		return Haveused;
	}
	
	public boolean getIsduePast() {
		return duePast;
	}
	
	public void setStatus(boolean b)
	{
		Haveused=b;
	}
	
	public void setIsduePast(boolean b) {
		duePast=b;
	}
	
}
