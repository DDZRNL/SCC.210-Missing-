import java.io.IOException;
import java.util.ArrayList;
import org.jsfml.system.Clock;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;

public class Launcher {
	private int gameWidth = 1280;
	private int gameHeight = 720;
	private String gameTitle = "Game Launcher";
	private ArrayList<Asset> objects = new ArrayList<Asset>();
	private RenderWindow pane;
	private static SoundPlay sound=new SoundPlay();
	private Floor[] f=new Floor[20];
	private Player p;
	private Map Map;

	public Launcher() throws IOException {
		pane = new RenderWindow();
		pane.create(new VideoMode(gameWidth,gameHeight),gameTitle,WindowStyle.DEFAULT);
		pane.setVerticalSyncEnabled(true);
		Map = new Map(objects, pane);
		Map.map1();
		p=Map.getPlayer();
		Map.getEnemy();

		sound.cave();



		while(pane.isOpen()) {

			if(p.xPosition>(gameWidth+180))    //Gate in right
			{
				if(Map.getEnemyNum()>0||Map.getmapNum()==5)      //can not move to next map before kill all enemies or it is final map
				{
					p.setXPosition(gameWidth+180);
				}
				else
				{
					objects.clear();
					Map.leftTrue();
					Map.mapNumincrease();
					Map.updateMap();
					p=Map.getPlayer();
					Map.getEnemy();
				}
				
			}
			else if(p.xPosition<160)       //Gate in left  160
			{
				if(Map.getEnemyNum()>0||Map.getmapNum()==1)    //can not move to last map before kill all enemies or it is first map
				{
					p.setXPosition(160);
				}
				else
				{
					objects.clear();
					Map.leftFalse();
					Map.mapNumdecrease();
					Map.updateMap();
					p=Map.getPlayer();
					Map.getEnemy();
				}
			}
			pane.clear(Color.BLACK);
			for(int j=0;f[j]!=null;j++)
			{
				f[j].draw(pane);
			}
			for(int i=0;i<objects.size();i++) {
				if(objects.get(i).getClass()==HeavyBandit.class)
					if(((HeavyBandit) objects.get(i)).getLiving()==true)
						((HeavyBandit) objects.get(i)).stateCheck(p);
				if(objects.get(i).getClass()==Player.class) {
					p.move();
					p.Potionwork();
				}
				objects.get(i).draw(pane);
			}
			System.out.println("PLAYERY:"+p.getYPosition());
			System.out.println("PLAYERX:"+p.getXPosition());
			pane.display();
			for(Event e: pane.pollEvents()) {
				if(e.type == Event.Type.CLOSED) {
					pane.close();
				}
			}
			Map.checkPotion();
		}

	}


	public static void main(String[] args) throws IOException {
		Launcher game = new Launcher();
	}

}
