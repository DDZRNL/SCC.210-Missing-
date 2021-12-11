import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.system.Clock;
import org.jsfml.window.Keyboard;

public class Player extends Asset {
    private Sprite pSprite;
    private IntRect pFrame;

    private RenderWindow pane;

    private Health healthbar;
    private Write playerHealth;

    private RectangleShape fadeOut = new RectangleShape(new Vector2f(1280, 720));

    private static String tFile = "src/Spritesheet/LightBandit_Spritesheet.png";

    private int frameWidth = 48;
    private int frameHeight = 48;
    private int playerWidth = 48;
    private int playerHeight = 48;

    private Clock timer;
    private Clock footstepClock;
    private Clock jumpClock;
	private Clock jumpCD;
    private Clock slashClock;
    private Clock hitClock;
    private int hitCount = 0;
    private Clock slashTimer;

    int i = 0;

    private int[] idleAnimation = {0,0,3};
    private int[] movingAnimation = {0,48,7};
    private int[] battleAnimation = {192,0,3};
    private int[] slashAnimation = {96,96,5};
    private int[] hitAnimation = {0,192,0};
    private int[] recoveringAnimation = {0,144,7};
    private int[] deadAnimation = {288,192,0};
    private int[] dyingAnimation = {336, 144, 7};
    //private boolean hit = false;
    private boolean canMove = true;
    private boolean canJump = true;
    private boolean canSlash = true;

    private boolean idle = true;
    private boolean moving = false;
    private boolean dead = false;
    private boolean battle = false;
    private boolean slash = false;
    private boolean recovery = false;
    private boolean damaged = false;

    private boolean faceRight = true;

    private boolean velocityDecreasing = false;

    private double originYPosition=0;

    private Write gameOver;

    public double speed = 10f;
    private double jumpSpeed = 10f;   //2f  2.7f
    private SoundPlay sound=new SoundPlay(); //Tony

    //Gravity Variables
    double velocityX = 0;
    double velocityXlast;
    double velocityY = 0;
    int groundHeight;
    float gravity = 2f;    //2f
    boolean isJumping = false;
    int jumpHeight = 100;   //75

    Floor[] f;   //Tony
    Potion[] potion=new Potion[15];   //T
    Object[] potionimg=new Object[15];  //T
    
    long start;
    long end;
    long totalTime=0;
    int damage=1;

    public void mirrorSprite() {
        if (getFaceRight())
            frameWidth = -playerWidth;
        else
            frameWidth = playerWidth;
    }

    public boolean getSlash(){
        return slash;
    }

    public void setGroundHeight(int groundHeight) {
        this.groundHeight = groundHeight;
    }

    public void updateSprite(int frameX, int frameY) {
        pFrame = new IntRect(frameX,frameY,frameWidth,frameHeight);
        pSprite.setTextureRect(pFrame);
    }

    public void update() {

        int start;
        if (getFaceRight()) start = 48;
        else start = 0;

        if(timer.getElapsedTime().asSeconds() >=0.1f) {
            if(idle == true)
                if(idleAnimation[0]<playerWidth*(idleAnimation[2])+start) {
                    idleAnimation[0] += playerWidth;
                    updateSprite(idleAnimation[0],idleAnimation[1]);
                }
                else {
                    idleAnimation[0] = 0+start;
                    updateSprite(idleAnimation[0],idleAnimation[1]);
                }
            else if(moving == true)
                if(movingAnimation[0]<playerWidth*(movingAnimation[2])+start) {
                    movingAnimation[0] += playerWidth;
                    updateSprite(movingAnimation[0],movingAnimation[1]);
                }
                else {
                    movingAnimation[0] = 0+start;
                    updateSprite(movingAnimation[0],movingAnimation[1]);
                }
            else if(battle == true)
                if((battleAnimation[0]-192)<playerWidth*(battleAnimation[2])+start) {

                    battleAnimation[0] += playerWidth;
                    updateSprite(battleAnimation[0],battleAnimation[1]);
                }
                else {
                    battleAnimation[0] = 192+start;
                    updateSprite(battleAnimation[0],battleAnimation[1]);
                }
            else if(slash == true)
                if((slashAnimation[0]-96)<playerWidth*(slashAnimation[2])+start) {
                    slashAnimation[0] += playerWidth;
                    updateSprite(slashAnimation[0],slashAnimation[1]);

                }
                else {
                    slashAnimation[0] = 96+start;
                    updateSprite(slashAnimation[0],slashAnimation[1]);
                    slash = false;
                }
            else if(recovery == true || damaged)
                if(recoveringAnimation[0]<playerWidth*(recoveringAnimation[2])+start) {
                    recoveringAnimation[0] += playerWidth;
                    updateSprite(recoveringAnimation[0],recoveringAnimation[1]);
                }
                else {
                    System.out.println(recoveringAnimation[0]);
                    recoveringAnimation[0] = 0+start;
                    damaged = false;
                }
            else if(hit == true)
                if(hitAnimation[0]<playerWidth*(hitAnimation[2])+start) {
                    hitAnimation[0] += playerWidth;
                    updateSprite(hitAnimation[0],hitAnimation[1]);
                }
                else {
                    hitAnimation[0] = 0+start;
                    updateSprite(hitAnimation[0],hitAnimation[1]);
                }
            else if(dead == true) {
                if (dyingAnimation[0] > 0) {
                    dyingAnimation[0] -= playerWidth;
                    updateSprite(dyingAnimation[0], dyingAnimation[1]);
                }
                else {
                    deadAnimation[0] = 288+start;
                    updateSprite(deadAnimation[0],deadAnimation[1]);
                }

            }
            timer.restart();
        }
    }

    public void resetBool() {
        int n=0;
        hit = false;
        idle = false;
        moving = false;
        dead = false;
        battle = false;
        slash = false;
        recovery = false;

    }

    public void hit() {
        hit = true;
    }

    public void move() throws IOException {

        update(); //Update spritesheet

        /*
            Draw health text and healthbar
        */
        playerHealth.draw(pane);
        healthbar.draw(pane);
        healthbar.update();

        if (!isJumping) velocityY = 0; //Reset Y velocity
        velocityXlast = velocityX; //Store X velocity of last frame to compare to current for movement

        /*
            Movement LEFT
        */
        if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) && !slash && canMove) {
            canSlash = true;
            velocityDecreasing = false;
            setFaceRight(false);

            //Acceleration when first pressed
            if (velocityX >= -speed) {
                if (speed + velocityX <= 0.1) velocityX = -speed;
                else {
                    velocityX += -(speed/50);
                }
            }
            else velocityX = -speed;
        }
        /*
            Movement RIGHT
        */
        if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT) && !slash && canMove) {
            canSlash = true;
            velocityDecreasing = false;
            setFaceRight(true);

            //Acceleration when first pressed
            if (velocityX <= speed) {
                if (speed - velocityX <= 0.1) velocityX = speed;
                else velocityX += (speed/50);

            }
            else velocityX = speed;
        }

        /*
            Decrease velocity when key released --> player slow down
        */
        if (velocityXlast == velocityX || velocityDecreasing) {
            velocityX *= 0.9;
            velocityDecreasing = true;

            //Threshold to reset to 0 so player does not infinitely slow to very small values
            if ((velocityX < speed/7 && velocityX > 0) || (velocityX > -speed/7 && velocityX < 0)) {
                velocityX = 0;
                velocityDecreasing = false;
            }
        }

        /*
            Move the player in the += velocity worked out in the above code
        */
        xPosition += velocityX;

        /*
                            SOUNDS
        */
        //Walking
        if (footstepClock.getElapsedTime().asSeconds() >= 0.75 && moving) {
            if(!isJumping) {
                sound.walking();
            }
            footstepClock.restart();
        }
        //Jumping
        if (jumpClock.getElapsedTime().asSeconds() >= 0.75 && Keyboard.isKeyPressed(Keyboard.Key.SPACE) && !dead) {
            sound.jump();
            jumpClock.restart();
        }
        //Slashing
        if (slashClock.getElapsedTime().asSeconds() >= 1 && slash) {
            sound.attack();
            slashClock.restart();
        }
        //Been hit
        if (hitClock.getElapsedTime().asSeconds() >= 1 && hit) {
            sound.injured();
            hitClock.restart();
        }



        /*
            Set moving animation if player is moving
        */
        if (velocityX != 0) {
            if (!velocityDecreasing) {
                resetBool();
                moving = true;
            }
        }

        /*
            Set idle animation if no other animation is currently playing
        */
        if(velocityX == 0 && !hit && !dead && !battle && !slash && !recovery) {
            resetBool();
            idle = true;
        }

        /*
            Player falls when the floor height differs from the player's y position
        */
        for(int i=0;f[i]!=null;i++)
        {
            if(yPosition==f[i].getYPosition()&&(xPosition<f[i].getXPosition()||xPosition>(f[i].getXPosition()+f[i].getLength())))
            {
                //yPosition = f[i].getYPosition();
                isJumping=true;
                velocityY=1;         //Vy= gt2/2
                canJump = false;
            }
        }

        /*
            Player jumps when space is hit
        */
        if (Keyboard.isKeyPressed(Keyboard.Key.SPACE) && canJump) {
			if(jumpCD.getElapsedTime().asSeconds()>=0.75f){
				isJumping = true;
				originYPosition=yPosition;   //Tony
				velocityY = -jumpSpeed;
				canJump = false;
				jumpCD.restart();
			}
        }


        /*
            Jump height/velocity calculations
        */
        if (isJumping) {
            yPosition += velocityY;

            if (velocityY < 0) velocityY *= 0.9;
            else velocityY *= 1.25;

            if (0-velocityY < 0.5 && velocityY < 0) {
                velocityY = 0.1;
            }

            if (yPosition >= groundHeight) {
                isJumping = false;
                yPosition = groundHeight;
            }

            /*
                Tony: Jump to another floor
            */
            if(velocityY>=0)
            {
                for(int i=0;f[i]!=null;i++)
                {
                    double h=originYPosition-f[i].getYPosition();

                    if(yPosition>=f[i].getYPosition()&&(xPosition>=f[i].getXPosition()&&xPosition<=(f[i].getXPosition()+f[i].getLength()))&&h<jumpHeight)
                    {
                        isJumping = false;
                        yPosition = f[i].getYPosition();
                        originYPosition=yPosition;
                    }

                }
            }
            
            /*
                Tony: Player hits his head under the obstacle
            */
            if(velocityY<=0)
            {
            	for(int i=0;f[i]!=null;i++)
                {
            		if(xPosition>=f[i].getXPosition()&&xPosition<=(f[i].getXPosition()+f[i].getLength())&&(f[i].getYPosition()<yPosition-playerHeight))
            		{
            			if((f[i].getYPosition()+f[i].getWidth())>=(yPosition-playerHeight)&&f[i].isBlock())
            			{
            				yPosition=f[i].getYPosition()+f[i].getWidth()+playerHeight;
            				//velocityY+=1f;
            				velocityY-=2*velocityY;   //Take the inverse of this number
            			}

            		}
                } 
            }   
            
            /*
                Tony: Block stop the player shuffling
            */
            if(velocityX!=0)
            {
            	for(int i=0;f[i]!=null;i++)
            	{
            		if((yPosition-playerHeight)<=(f[i].getYPosition()+f[i].getWidth())&&(yPosition>f[i].getYPosition()))
            		{
            			if(xPosition>=f[i].getXPosition()&&xPosition<=(f[i].getXPosition()+f[i].getLength())&&f[i].isBlock())
                		{
                			velocityX=0;
                		}
            		}
            	}
            }
        }

        /*
            Player can jump if they are on the ground and are not currently jumping
        */
        if (yPosition == groundHeight && !isJumping) {
            canJump = true;
        }

        //checks the floors relative to the player position to see if the player is allowed to jump
        for(int i=0;f[i]!=null;i++)
        {
            if (yPosition == f[i].getYPosition() && !isJumping) {
                canJump = true;
            }
        }


        /*
            Player can slash when c is pressed
        */
        if (Keyboard.isKeyPressed(Keyboard.Key.C) && canSlash) {
            resetBool();
            slash = true;
            moving = false;
            canSlash = false;
            slashTimer.restart();
        }
        //Can slash once a second, to stop too much damage to the enemy
        //(waits for slash amination to finish before slashing again)
        if (slashTimer.getElapsedTime().asSeconds() > 1.5) {
            canSlash = true;
        }

        /*
            Stops the player from moving if they are hit
        */
        if (hit == true) {
            hitCount++;
            if (hitCount <= 10) {
                resetBool();
                hit = true;
                canSlash = false;
            }
            else {
                hitCount = 0;
                resetBool();
                idle = true;
            }
        }

        /*
            When health is zero, player is dead
        */
        if (health == 0) {
            resetBool();
            dead = true;
            canJump = false;
            canMove = false;

            pane.draw(fadeOut);
            fadeOut.setFillColor(new Color(0, 0, 0, i/10));
            i++;

            if (gameOver.getSize() < 450) {
                gameOver.incSize();
            }
            gameOver.draw(pane);


        }
        mirrorSprite(); //Put player in right direction
        setPosition = pSprite::setPosition; //Set player position
    }

    public Player(int x, int y, Floor[] f, RenderWindow pane, Potion[] potion) {      //Tony
        setXPosition(x);
        setYPosition(y);
        this.pane = pane;
        this.f=f;
        this.potion=potion;
        gameOver = new Write(10, 0, "GAME OVER", 0, Color.RED);

        this.health = 5;
        playerHealth = new Write(10, 660, "HEALTH", 50, Color.WHITE);

        healthbar = new Health(110, 673, 200, 35, this);

        footstepClock = new Clock();
        jumpClock = new Clock();
        slashClock = new Clock();
        hitClock = new Clock();
		jumpCD = new Clock();

        fadeOut.setPosition(new Vector2f(0, 0));
        fadeOut.setFillColor(Color.TRANSPARENT);

        //groundHeight = y;

        Texture image = new Texture();
        try {
            image.loadFromFile(Paths.get(tFile));
        } catch(Exception e) {
            e.printStackTrace();
        }

        pFrame = new IntRect(0, 0, frameWidth, frameHeight);
        pSprite = new Sprite(image, pFrame);

        pSprite.setOrigin(Vector2f.div(new Vector2f(image.getSize()),2));

        item = pSprite;
        setPosition = pSprite::setPosition;

        timer = new Clock();
        slashTimer = new Clock();
    }
    
    public void Potionwork()
    {
    	for(int i=0;potion[i]!=null;i++)
    	{
    		if(potion[i].getYPosition()<=yPosition&&(potion[i].getYPosition()+75)>=yPosition) {
    			if(potion[i].getXPosition()<=xPosition&&(potion[i].getXPosition()+30)>=xPosition)
    			{
    				if(potion[i].getStatus()==false)
    				{
    					if(potion[i].getType()==1)     //Health
        				{
    						setHealth(5);
        					//health=5;
        					healthbar = new Health(110, 673, 200, 35, this);
        					//yPosition=300;
        				}
        				else if(potion[i].getType()==2)  //Jump
        				{
        					jumpSpeed = 4f;  //3.5f
        					jumpHeight = 110;   //75
        				}
        				else if(potion[i].getType()==3)  //Damage
        				{
        					damage=2;
        				}
        				else if(potion[i].getType()==4)  //Speed
        				{
        					speed=3f;
        				}
    					potion[i].setStatus(true);
    					
    					//resetProperty();
    					timer.restart();
    					start=System.currentTimeMillis();
    					//System.out.println(start);
    					
    				}
    			}
    		}
    		
    		if(potion[i].getStatus()&&!potion[i].getIsduePast())
    		{
    			end=System.currentTimeMillis();
    			totalTime=(end-start)/1000;   //ms to s
    			
    			if(totalTime>=15) {          //insist 15 sec
    				//System.out.println(end);
    				if(potion[i].getType()==2) {
    					jumpSpeed=30f;
    					jumpHeight = 100;   //75
    				}
    				else if(potion[i].getType()==3) {
    					damage=1;
    				}
    				else if(potion[i].getType()==4) {
    					speed=20f;
    				}
    				resetProperty();
    				potion[i].setIsduePast(true);
    				//System.out.println("Drug over!!!");
    			}
    		}
    	}
    }

    public double getJumpHeight() {
        return jumpHeight;
    }
    
    public double getXPosition()
    {
    	return xPosition;
    }
    
	public double getYPosition()
    {
    	return yPosition;
    }
	
    public void setXPosition(double x)
    {
    	xPosition=x;
    }
    
    public void addHealth()
    {
    	health+=1;
    }

    public Potion[] getPotions()
    {
    	return potion;
    }
    
    public void resetProperty()
    {
    	jumpSpeed=10f;
    	speed=10f;
    }
    
    public int getDamage()
    {
    	return damage;
    }

    public void checkSpeed(int enemiesKilled) {
        speed = (((float)enemiesKilled/2) + speed);
    }

	public boolean getFaceRight() {
		return faceRight;
	}

	public void setFaceRight(boolean faceRight) {
		this.faceRight = faceRight;
	}

}
