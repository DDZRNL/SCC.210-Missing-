import java.io.IOException;
import java.util.ArrayList;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;

public class HeavyBandit extends Monster{
	private static int width = 48;
	private static int height = 48;
	private static String tFile = "src/Spritesheet/BlueBandit_SpriteSheet.png";
	private static int radiusCheck = 350;
	private static int detectCheck = 150;
	private static int startingX = 0;
	private static int startingY = 0;
	private static double defaultSpeed = 3;
	private static int spaceGap = 25;
	private String tF;
	private int leftEnd = 75;
	private int rightEnd = 75;
	//private int health = 4;
	private Clock timer;
	private Clock slashTimer;
	private Clock hitTimer;
	private Clock moveTimer;

	int i = 0;

	private Map map;
	private SoundPlay sound = new SoundPlay();

	/*
	Pic 1: 0-48
	Pic 2: 48-96
	Pic 3: 96-144
	Pic 4: 144-192
	Pic 5: 192-240
	Pic 6: 240-288
	Pic 7: 288-336
	Pic 8: 336-384
	*/
	private int[] idleAnimation = {0,0,3};
	private int[] movingAnimation = {0,48,7};
	private int[] battleAnimation = {192,0,3};
	private int[] slashAnimation = {96,96,5};
	private int[] hitAnimation = {0,192,0};
	private int[] recoveringAnimation = {0,144,7};
	private int[] deadAnimation = {288,192,2};
	private int[] dyingAnimation = {336, 144, 7};
	//private boolean hit = false;
	private boolean idle = true;
	private boolean moving = false;
	private boolean dead = false;
	private boolean battle = false;
	private boolean slash = false;
	private boolean recovery = false;
	private boolean faceRight = false;
	private boolean damaged = false;

	private RenderWindow pane;

	private Health healthBar;
	
	public void resetBool() {
		hit = false;
		idle = false;
		moving = false;
		dead = false;
		battle = false;
		slash = false;
		recovery = false;
	}

	public boolean getHit() {
		return hit;
	}
	public int getHealth() {
		return health;
	}

	public void mirrorSprite() {
		if (faceRight)
			frameWidth = -48;
		else
			frameWidth = 48;
	}
	
	public void TypeDecide(int type)
	{
		if(type==1) {
			tF = "src/Spritesheet/HeavyBandit_SpriteSheet.png";
		}
		else if(type==2) {
			tF = "src/Spritesheet/GreenBandit_SpriteSheet.png";
		}
		else if(type==3) {
			tF = "src/Spritesheet/BlueBandit_SpriteSheet.png";
		}
		else if(type==4) {
			tF = "src/Spritesheet/PurpleBandit_SpriteSheet.png";
		}
	}
	
	public void checkPlayer(Player a) {

		if(a.getSlash()==true && a.yPosition >= this.yPosition-a.getJumpHeight() && a.yPosition <= this.yPosition+a.getJumpHeight() && moving == false) {
			if(faceRight == false && xPosition-a.xPosition < 25 && a.getFaceRight()==true) {
				resetBool();
				hit = true;
				damaged = true;
			}
			else if(faceRight == true && a.xPosition-xPosition < 25 && a.getFaceRight()==false) {
				resetBool();
				hit = true;
				damaged = true;
			}
		}
		else if(damaged == true && health > 1) {
				resetBool();
				recovery=true;
		}
		else if(damaged == true && health <= 0) {
			resetBool();
			dead=true;
		}
		else if(a.xPosition < this.xPosition-spaceGap && a.xPosition <= this.xPosition+radiusCheck && a.xPosition >= this.xPosition-radiusCheck && a.yPosition >= this.yPosition-a.getJumpHeight() && a.yPosition <= this.yPosition+a.getJumpHeight() && this.xPosition > startingX-leftEnd) {
			resetBool();
			moving = true;
			setXPosition(this.xPosition-defaultSpeed);
		}
		else if(a.xPosition > this.xPosition+spaceGap && a.xPosition <= this.xPosition+radiusCheck && a.xPosition >= this.xPosition-radiusCheck && a.yPosition >= this.yPosition-a.getJumpHeight() && a.yPosition <= this.yPosition+a.getJumpHeight() && this.xPosition < startingX+rightEnd) {
			resetBool();
			moving = true;
			setXPosition(this.xPosition+defaultSpeed);
		}
		else if(a.xPosition <= this.xPosition+spaceGap && a.xPosition >= this.xPosition-spaceGap && a.getHealth() > 0 && a.yPosition >= this.yPosition-a.getJumpHeight() && a.yPosition <= this.yPosition+a.getJumpHeight()) {
			resetBool();
			slash = true;
			if (a.yPosition == yPosition) {
				i++;
				if (i >= 10) {
					a.hit();
					i = 0;
				}
			}
		}
		else {
			if(a.xPosition < this.xPosition-spaceGap-detectCheck && a.xPosition <= this.xPosition+radiusCheck+detectCheck && a.xPosition >= this.xPosition-radiusCheck-detectCheck&&moving==false && a.yPosition >= this.yPosition-a.getJumpHeight() && a.yPosition <= this.yPosition+a.getJumpHeight()) {
				resetBool();
				battle = true;
			}
			else if(a.xPosition > this.xPosition+spaceGap-detectCheck && a.xPosition <= this.xPosition+radiusCheck+detectCheck && a.xPosition >= this.xPosition-radiusCheck-detectCheck&&moving==false && a.yPosition >= this.yPosition-a.getJumpHeight() && a.yPosition <= this.yPosition+a.getJumpHeight()) {
				resetBool();
				battle = true;
			}
			else {
				resetBool();
				idle = true;
			}
		}
	}
	
	public void stateCheck(Player a) throws IOException {
		healthBar.setDamage(a.getDamage());
		checkPlayer(a);
		healthBar.draw(pane);
		healthBar.follow();

		/*
							SOUNDS
		*/
		//Moving
		if (moveTimer.getElapsedTime().asSeconds() >= 0.75 && moving) {
			sound.walking();
			moveTimer.restart();
		}
		//Slashing
		if (slashTimer.getElapsedTime().asSeconds() >= 1 && slash) {
			sound.attack();
			slashTimer.restart();
		}
		//Been hit
		if (hitTimer.getElapsedTime().asSeconds() >= 1 && hit) {
			sound.injuredEnemy();
			hitTimer.restart();
		}

		if(timer.getElapsedTime().asSeconds() >=0.15f) {

			if(a.xPosition > xPosition) faceRight = true;
			else if(a.xPosition < xPosition) faceRight = false;

			mirrorSprite();

			int start;
			if (faceRight) start = 48;
			else start = 0;


			if(idle == true)
				if(idleAnimation[0]<width*(idleAnimation[2])+start) {
					idleAnimation[0] += width;
					updateSprite(idleAnimation[0],idleAnimation[1]);
				}
				else {
					idleAnimation[0] = 0+start;
					updateSprite(idleAnimation[0],idleAnimation[1]);
				}
			else if(moving == true)
				if(movingAnimation[0]<width*(movingAnimation[2])+start) {
					movingAnimation[0] += width;
					updateSprite(movingAnimation[0],movingAnimation[1]);
				}
				else {
					movingAnimation[0] = 0+start;
					updateSprite(movingAnimation[0],movingAnimation[1]);
				}
			else if(battle == true)
				if((battleAnimation[0]-192)<width*(battleAnimation[2])+start) {

					battleAnimation[0] += width;
					updateSprite(battleAnimation[0],battleAnimation[1]);
				}
				else {
					battleAnimation[0] = 192+start;
					updateSprite(battleAnimation[0],battleAnimation[1]);
				}
			else if(slash == true)
				if((slashAnimation[0]-96)<width*(slashAnimation[2])+start) {
					slashAnimation[0] += width;
					updateSprite(slashAnimation[0],slashAnimation[1]);
				}
				else {
					slashAnimation[0] = 96+start;
					updateSprite(slashAnimation[0],slashAnimation[1]);
				}
			else if(recovery == true)
				if(recoveringAnimation[0]<width*(recoveringAnimation[2])+start) {
					recoveringAnimation[0] += width;
					updateSprite(recoveringAnimation[0],recoveringAnimation[1]);
				}
				else {
					recoveringAnimation[0] = 0+start;
					damaged = false;
				}
			else if(hit == true)
				if(hitAnimation[0]<width*(hitAnimation[2])+start) {
					hitAnimation[0] += width;
					updateSprite(hitAnimation[0],hitAnimation[1]);
				}
				else {
					hitAnimation[0] = 0+start;
					updateSprite(hitAnimation[0],hitAnimation[1]);
				}
			else if(dead == true) {
				if (dyingAnimation[0] > 0) {
					dyingAnimation[0] -= width;
					updateSprite(dyingAnimation[0], dyingAnimation[1]);
				}
				else {
					deadAnimation[0] = 288+start;
					updateSprite(deadAnimation[0],deadAnimation[1]);
					if(health<=0) {
						setLiving(false);
						setXPosition(9999);
						setYPosition(9999);
						map.incEnemiesKilled();
						map.EnemeyNumReduce();
					}
				}
			}
			timer.restart();
		}
	}
	public HeavyBandit(int x, int y, RenderWindow pane, int leftEnd, int rightEnd, ArrayList<Asset> list, Map map, int health, int type,Player p) {
		super(width, height, tFile, 4, list);
		TypeDecide(type);
		updateImg(tF);
		this.health = health;
		setLiving(true);
		this.pane = pane;
		hitTimer = new Clock();
		moveTimer = new Clock();
		slashTimer = new Clock();
		this.map = map;
		healthBar = new Health(this);
		this.leftEnd = leftEnd;
		this.rightEnd = rightEnd;
		this.startingX = x;
		this.startingY = y;
		setXPosition(x);
		setYPosition(y);
		timer = new Clock();
		slashTimer = new Clock();
		healthBar.setDamage(p.getDamage());
	}

}