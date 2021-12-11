import java.util.ArrayList;
import org.jsfml.graphics.*;


public class Map {
	private int gameWidth = 1280;
	private int gameHeight = 720;
	private ArrayList<Asset> objects;
	private int mapNum=1;
	private Background b=null;
	private Floor[] f=new Floor[20];            
	private Player p=null;
	private Potion[] potion=new Potion[15];   //T
	private Object[] potionimg=new Object[15];   //T
	private HeavyBandit[] enemy=new HeavyBandit[20];
	private boolean startleft=true;
	private RenderWindow pane;
	private int enemyNum=0;         //To count the enemy alive, after all the enemies died, the player can go to next map
	private int enemiesKilled=0;
	
	public Map(ArrayList<Asset> objects, RenderWindow pane)
	{
		this.objects=objects;
		this.pane = pane;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public HeavyBandit[] getEnemy()
	{
		return enemy;
	}
	
	public Floor[] getfloor()
	{
		return f;
	}
	
	public void mapNumincrease()
	{
		mapNum++;
	}
	
	public void mapNumdecrease()
	{
		mapNum--;
	}
	
	public void leftTrue()
	{
		startleft=true;
	}
	public void leftFalse()
	{
		startleft=false;
	}
	
	public void updateMap()
	{
		if(mapNum==1) {
			map1();
		}
		else if(mapNum==2)
		{
			map2();
		}
		else if(mapNum==3)
		{
			map3();
		}
		else if(mapNum==4)
		{
			map4();
		}
		else if(mapNum==5)
		{
			map5();
		}
	}
	
	public void enemyReset()
	{
		for(int i=0;enemy[i]!=null;i++)
		{
			enemy[i]=null;
		}
	}
	
	public void Reset() {
		for(int i=0;f[i]!=null;i++)
		{
			f[i]=null;
		}
		for(int i=0;potion[i]!=null;i++)
		{
			potion[i]=null;
		}
		for(int i=0;potionimg[i]!=null;i++)
		{
			potionimg[i]=null;
		}
	}
	
	//First map
	public void map1()
	{
		//objects.clear();
		Background b = new Background("src/Map/mapPageOne.png");

		Write text = new Write(290, 575, "Walk over this potion to get a damage buff", 30, Color.WHITE);
		Write text2 = new Write(847, 310, "Walk over this potion to restore health", 30, Color.RED);

		Write level1 = new Write(1175, 0, "LEVEL 1", 50, Color.WHITE);



		b.setOrigin(0, 0);
		objects.add(b);
		objects.add(text);
		objects.add(text2);
		text.draw(pane);
		text2.draw(pane);
		objects.add(level1);
		level1.draw(pane);
		//Reset
		enemiesKilled=0;
		for(int i=0;f[i]!=null;i++)
		{
			f[i]=null;
		}
		 //Tony
		f[0]=new Floor(340,650,30,80,true);       //Tony    165,530,30,80  // 650
		f[1]=new Floor(470,575,30,65,true);
		f[2]=new Floor(325,500,30,70,true);        //160,380,30,80
		f[3]=new Floor(470,425,35,995,true);       //The longest one  305,305,45,975 //470,425,35,985

		potion[0]=new Potion(600,647,objects,3,pane);    //600,687   150,100
		potionimg[0]=new Object(450,547,objects,3);      //450,547
		//potion[1]=new Potion()
		//potion[1]=new Potion(340,460,objects,2,pane);
		//potionimg[1]=new Object(190,360,objects,2);
		potion[1]=new Potion(1150,390,objects,1,pane);
		potionimg[1]=new Object(1000,290,objects,1);

		//Player and enemy position
		if(startleft)
		{
		   p = new Player(240,687,f, pane, potion);
		}
		else
		{
		   p = new Player(gameWidth+100,687,f, pane, potion);
		}
		p.setGroundHeight(687);
		objects.add(p);
		p.draw(pane);
		enemyReset();

        enemy[0] = new HeavyBandit(900,687, pane, 1280,1280 ,objects, this, 2, 2, p);
        enemy[1] = new HeavyBandit(1010,687, pane, 1280,1280, objects, this, 2, 2, p);
        enemy[2] = new HeavyBandit(900,425, pane, 420,750 ,objects, this, 2, 2, p);


		
		enemyNum=3;
		mapNum=1;
	}
	
	public void map2()
	{
		//objects.clear();
		Background b = new Background("src/Map/mapPageTwo.png");
		b.setOrigin(0, 0);
		objects.add(b);

		enemiesKilled=0;
		for(int i=0;f[i]!=null;i++)
		{
			f[i]=null;
		}

		Write level2 = new Write(1175, 0, "LEVEL 2", 50, Color.WHITE);
		objects.add(level2);
		level2.draw(pane);
		
		f[0]=new Floor(425,650,30,70,false);       //340,650,30,80,false
		f[1]=new Floor(540,575,30,70,false);
		f[2]=new Floor(405,530,30,70,false);
		f[3]=new Floor(540,455,30,70,false);
		f[4]=new Floor(405,410,30,70,false);
		f[5]=new Floor(540,335,30,70,false);
		f[6]=new Floor(405,290,30,70,false);
		f[7]=new Floor(540,220,30,70,false);
		f[8]=new Floor(405,180,30,70,false);
		
		f[9]=new Floor(145,550,45,195,true);        //160,550,45,180,true
		f[10]=new Floor(145,430,45,195,true);       //160,430,45,180,true
		f[11]=new Floor(145,310,45,195,true);        //160,310,45,180,true
		f[12]=new Floor(145,190,45,195,true);       //160,190,45,180,true
		f[13]=new Floor(880,425,45,600,true);     //Need to change  880,425,45,600,true
		
		f[14]=new Floor(680,525,20,90,false);
		f[15]=new Floor(770,500,20,35,false);
		
		potion[0]=new Potion(550,345,objects,3,pane);    //600,687   150,100
		potionimg[0]=new Object(400,195,objects,3);      //450,547
		potion[2]=new Potion(1350,390,objects,1,pane);
		potionimg[2]=new Object(1200,290,objects,1);

		
		
		if(startleft==true)
		{
		   p = new Player(240,687,f, pane, potion);
		}
		else
		{
		   p = new Player(gameWidth+100,687,f, pane, potion);
		}
		p.setGroundHeight(687);
		objects.add(p);
		enemyReset();
		enemy[0] = new HeavyBandit(900,687, pane, 1280,1280 ,objects, this, 5, 3, p);
		enemy[1] = new HeavyBandit(1000,687, pane, 1280,1280 ,objects, this, 5, 3, p);
		enemy[2] = new HeavyBandit(1100,425, pane, 220,1280 ,objects, this, 5, 3, p);
		
		enemyNum=3;
		mapNum=2;
	}
	
	public void map3()
	{
		Background b = new Background("src/Map/mapPageThree.png");
		b.setOrigin(0, 0);
		objects.add(b);

		enemiesKilled=0;
		for(int i=0;f[i]!=null;i++)
		{
			f[i]=null;
		} 
		
		f[0]=new Floor(280,610,30,100,false);      
		f[1]=new Floor(400,540,30,60,false);
		f[2]=new Floor(515,450,80,950,true);

		Write level3 = new Write(1175, 0, "LEVEL 3", 50, Color.WHITE);
		objects.add(level3);
		level3.draw(pane);
		
		potion[0]=new Potion(730,410,objects,3,pane);    //600,687   150,100
		potionimg[0]=new Object(580,310,objects,3);      //450,547
		potion[2]=new Potion(1350,410,objects,1,pane);
		potionimg[2]=new Object(1200,310,objects,1);
		
		if(startleft==true)
		{
		   p = new Player(240,675,f, pane, potion);
		}
		else
		{
		   p = new Player(gameWidth+100,675,f, pane, potion);
		}
		p.setGroundHeight(675);
		objects.add(p);
		enemyReset();
		enemy[0] = new HeavyBandit(900,675, pane, 1280,1280 ,objects, this, 8, 1, p);
		enemy[1] = new HeavyBandit(1100,675, pane, 1280,1280 ,objects, this, 8, 1, p);
		enemy[2] = new HeavyBandit(1050,450, pane, 1280,1280 ,objects, this, 8, 1, p);
		enemy[3] = new HeavyBandit(900,450, pane, 375,1280 ,objects, this, 8, 1, p);
		enemy[4] = new HeavyBandit(1000,450, pane, 375,1280 ,objects, this, 8, 1, p);
		enemy[5] = new HeavyBandit(1100,450, pane, 375,1280 ,objects, this, 8, 1, p);
		//enemy[1] = new HeavyBandit(1000,745);
		
		enemyNum=6;
		mapNum=3;
	}
	
	public void map4()
	{
		Background b = new Background("src/Map/mapPageFour.png");
		b.setOrigin(0, 0);
		objects.add(b);

		enemiesKilled=0;
		for(int i=0;f[i]!=null;i++)
		{
			f[i]=null;
		} 
		
		f[0]=new Floor(155,360,90,380,true);

		Write level4 = new Write(1175, 0, "LEVEL 4", 50, Color.WHITE);
		objects.add(level4);
		level4.draw(pane);
		
		potion[0]=new Potion(1350,705,objects,1,pane);
		potionimg[0]=new Object(1200,605,objects,1);
		potion[1]=new Potion(1150,705,objects,3,pane);
		potionimg[1]=new Object(1000,605,objects,3);
		
		if(startleft==true)
		{
		   p = new Player(240,745,f, pane, potion);
		}
		else
		{
		   p = new Player(gameWidth+100,745,f, pane, potion);
		}
		p.setGroundHeight(745);
		objects.add(p);
		enemyReset();
		enemy[0] = new HeavyBandit(900,745, pane, 1280,1280 ,objects, this, 20, 4, p);
		enemy[1] = new HeavyBandit(900,745, pane, 1280,1280 ,objects, this, 20, 4, p);
		
		enemyNum=2;
		mapNum=4;
	}
	
	public void map5()
	{
		Background b = new Background("Map/mapPageFive.png");
		Write winWord = new Write(170,250,"YOU WON! YOU WIN ETERNAL SUFFERING :)", 100, Color.WHITE);
		b.setOrigin(0, 0);
		objects.add(b);
		objects.add(winWord);
		winWord.draw(pane);
		enemiesKilled=0;
		for(int i=0;f[i]!=null;i++)
		{
			f[i]=null;
		} 
		
		f[0]=new Floor(155,360,90,380,true); 
		
		potion[0]=new Potion(1350,705,objects,1,pane);
		potionimg[0]=new Object(1200,605,objects,1);
		potion[1]=new Potion(1250,705,objects,1,pane);
		potionimg[1]=new Object(1100,605,objects,1);
		potion[2]=new Potion(1150,705,objects,1,pane);
		potionimg[2]=new Object(1000,605,objects,1);
		potion[3]=new Potion(1450,705,objects,1,pane);
		potionimg[3]=new Object(1300,605,objects,1);
		potion[4]=new Potion(1550,705,objects,1,pane);
		potionimg[4]=new Object(1400,605,objects,1);
		potion[5]=new Potion(1050,705,objects,1,pane);
		potionimg[5]=new Object(900,605,objects,1);
		potion[6]=new Potion(1650,705,objects,1,pane);
		potionimg[6]=new Object(1500,605,objects,1);
		
		if(startleft==true)
		{
		   p = new Player(240,745,f, pane, potion);
		}
		else
		{
		   p = new Player(gameWidth+100,745,f, pane, potion);
		}
		p.setGroundHeight(745);
		objects.add(p);
		enemyReset();
		enemy[0] = new HeavyBandit(900,745, pane, 1280,1280 ,objects, this, 2000000, 4, p);
		//enemy[1] = new HeavyBandit(1000,745);
		
		enemyNum=1;
		mapNum=5;
	}
	
	public int getEnemyNum()
	{
		return enemyNum;
	}
	public void EnemeyNumReduce() {
		p.checkSpeed(enemiesKilled);
		enemyNum--;
	}
	
	public int getmapNum()
	{
		return mapNum;
	}

	public void incEnemiesKilled() {
		enemiesKilled++;
	}

	public void checkPotion() {     //Make sure the potion image disappear after being used
		
		for(int i=0;potion[i]!=null;i++)
		{
			if(potion[i].getStatus()) {
				objects.remove(potionimg[i]);
			}
		}
	}
	
	
}
