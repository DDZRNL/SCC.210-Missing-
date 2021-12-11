import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tutorial {
	private RenderWindow window = new RenderWindow();
	ArrayList<Write> text = new ArrayList<Write>();
	ArrayList<Write> text2 = new ArrayList<Write>();
	ArrayList<Write> text3 = new ArrayList<Write>();
	ArrayList<Images> image = new ArrayList<Images>();
	ArrayList<Images> image2 = new ArrayList<Images>();
	ArrayList<Images> image3 = new ArrayList<Images>();
	ArrayList<PlayMusic> music = new ArrayList<PlayMusic>();
	private Font font = new Font();
	private Text nextButton = new Text();
	private Text backButton = new Text();
	private Text exitButton = new Text();

	public void pageOne() {
		window.create(new VideoMode(720, 720), "Tutorial");
		image.add(new Images(40f, 40f, 0.5f, 0.5f, "src/Map/mapPageOne.png"));
		image.add(new Images(10f, 445f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		text.add(new Write(40f, 450f, ".The player finds himself in an underground world and is surrounded", 35, Color.WHITE));
		text.add(new Write(40f, 495f, "by an underground mafia that does not like any kind of interference", 35,
				Color.WHITE));
		text.add(new Write(40f, 540f, "from the outside world.", 35, Color.WHITE));
		music.add(new PlayMusic("src/Sound/caveAmbience.wav"));
		music.add(new PlayMusic("src/Sound/buttonpress.wav"));
		try {
			font.loadFromFile(Paths.get("src/font.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		nextButton.setFont(font);
		nextButton.setString("Next >");
		nextButton.setColor(Color.WHITE);
		nextButton.setCharacterSize(35);
		nextButton.setPosition(600.0f, 600.0f);
		music.get(0).startMusic();
		while (window.isOpen()) {
			window.clear();
			for (Images i : image) {
				for (Write type : text) {
					i.draw(window);
					type.draw(window);
				}
			}
			window.draw(nextButton);
			if (nextButton.getGlobalBounds().contains(Mouse.getPosition(window).x, Mouse.getPosition(window).y)) {
				window.clear();
				for (Images i : image) {
					for (Write type : text) {
						i.draw(window);
						type.draw(window);
					}
				}
				nextButton.setColor(Color.RED);
				window.draw(nextButton);
				nextButton.setColor(Color.WHITE);
				if (Mouse.isButtonPressed(Mouse.Button.valueOf("LEFT")) == true) {
					music.get(1).startMusic();
					window.close();
					pageTwo();
				}
			}
			for (Event e : window.pollEvents()) {
				if (e.type == Event.Type.CLOSED) {
					window.close();
					music.get(0).stopMusic();
					Menu m = new Menu();
				}
			}
			window.display();
		}
	}

	public void pageTwo() {
		window.create(new VideoMode(730, 720), "Tutorial");
		image2.add(new Images(40f, 40f, 0.51f, 0.5f, "src/Map/mapPageTwo.png"));
		image2.add(new Images(20f, 450f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image2.add(new Images(20f, 495f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image2.add(new Images(20f, 575f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		text2.add(new Write(55f, 460f, ".The player needs to kill all the enemies to proceed onto the next stage.", 35,
				Color.WHITE));
		text2.add(new Write(55f, 500f, ".The player can jump over the objects to climb up and avoid any health", 35,
				Color.WHITE));
		text2.add(new Write(65f, 540, "damage by the enemies.", 35, Color.WHITE));
		text2.add(new Write(55f, 580, ".The enemies get harder to beat once the player levels up.", 35, Color.WHITE));
		try {
			font.loadFromFile(Paths.get("src/font.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		backButton.setFont(font);
		backButton.setString(">");
		backButton.setColor(Color.WHITE);
		backButton.setCharacterSize(75);
		backButton.setPosition(700.0f, 380.0f);
		while (window.isOpen()) {
			window.clear();
			for (Images i2 : image2) {
				for (Write w : text2) {
					i2.draw(window);
					w.draw(window);
				}
			}
			window.draw(backButton);
			if (backButton.getGlobalBounds().contains(Mouse.getPosition(window).x, Mouse.getPosition(window).y)) {
				window.clear();
				for (Images i2 : image2) {
					for (Write w : text2) {
						i2.draw(window);
						w.draw(window);
					}
				}
				backButton.setColor(Color.RED);
				window.draw(backButton);
				backButton.setColor(Color.WHITE);
				if (Mouse.isButtonPressed(Mouse.Button.valueOf("LEFT")) == true) {
					music.get(1).startMusic();
					pageThree();
				}
			}
			for (Event e : window.pollEvents()) {
				if (e.type == Event.Type.CLOSED) {
					window.close();
					music.get(0).stopMusic();
					Menu m = new Menu();
				}
			}
			window.display();
		}

	}

	public void pageThree() {
		window.create(new VideoMode(720, 720), "Tutorial");
		image3.add(new Images(40f, 40f, 0.5f, 0.5f, "src/Map/mapPageFour.png"));
		image3.add(new Images(30f, 440f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image3.add(new Images(30f, 485f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image3.add(new Images(30f, 530f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image3.add(new Images(30f, 575f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image3.add(new Images(425f, 535f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image3.add(new Images(425f, 580f, 0.5f, 0.5f, "src/Tutorial/t1.png"));
		image3.add(new Images(220f, 540f, 1f, 1f, "src/Potion/Damage.png"));
		image3.add(new Images(220f, 585f, 1f, 1f, "src/Potion/Health.png"));
		image3.add(new Images(580f, 540f, 1f, 1f, "src/Potion/Jump.png"));
		image3.add(new Images(580f, 585f, 1f, 1f, "src/Potion/Speed.png"));
		text3.add(
				new Write(65f, 450f, ".The player needs to kill the final boss to finish the game.", 35, Color.WHITE));
		text3.add(
				new Write(65f, 495f, ".The player can also find potions to use for 20 seconds each.", 35, Color.WHITE));
		text3.add(new Write(65f, 540f, ".Damage Potion", 35, Color.WHITE));
		text3.add(new Write(65f, 585f, ".Health Potion", 35, Color.WHITE));
		text3.add(new Write(460f, 540f, ".Jump Potion", 35, Color.WHITE));
		text3.add(new Write(460f, 585f, ".Speed Potion", 35, Color.WHITE));
		try {
			font.loadFromFile(Paths.get("src/font.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		exitButton.setFont(font);
		exitButton.setString("Exit");
		exitButton.setColor(Color.WHITE);
		exitButton.setCharacterSize(35);
		exitButton.setPosition(330.0f, 640.0f);
		while (window.isOpen()) {
			window.clear();
			for (Images i3 : image3) {
				for (Write w3 : text3) {
					i3.draw(window);
					w3.draw(window);
				}
			}
			window.draw(exitButton);
			if (exitButton.getGlobalBounds().contains(Mouse.getPosition(window).x, Mouse.getPosition(window).y)) {
				window.clear();
				for (Images i3 : image3) {
					for (Write w3 : text3) {
						i3.draw(window);
						w3.draw(window);
					}
				}
				exitButton.setColor(Color.RED);
				window.draw(exitButton);
				exitButton.setColor(Color.WHITE);
				if (Mouse.isButtonPressed(Mouse.Button.valueOf("LEFT")) == true) {
					music.get(1).startMusic();
					window.close();
					music.get(0).stopMusic();
					Menu m = new Menu();
				}
			}
			for (Event e : window.pollEvents()) {
				if (e.type == Event.Type.CLOSED) {
					window.close();
					music.get(0).stopMusic();
					Menu m = new Menu();
				}
			}
			window.display();
		}
	}

	public Tutorial() {
		pageOne();
	}

	public static void main(String[] args) {
		Tutorial tutorial = new Tutorial();
	}
}