import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Scoreboard {

	private RenderWindow scoreWindow = new RenderWindow();
	private Font font = new Font();
	private Text text = new Text();

	public Scoreboard() {
		scoreWindow.create(new VideoMode(500, 500), "Scoreboard");
		try {
			font.loadFromFile(Paths.get("font.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		text.setFont(font);
		text.setString("Scoreboard");
		text.setColor(Color.WHITE);
		text.setCharacterSize(50);
		text.setPosition(180.0f, 0.0f);
		while(scoreWindow.isOpen()) {
			scoreWindow.clear();
			scoreWindow.draw(text);
			for (Event e: scoreWindow.pollEvents()) {
				if (e.type == Event.Type.CLOSED) {
					scoreWindow.close();
					Menu m = new Menu();	
				}
			}
			scoreWindow.display();
		}
	}

	public static void main(String[] args) {
		Scoreboard s = new Scoreboard();
	}
}