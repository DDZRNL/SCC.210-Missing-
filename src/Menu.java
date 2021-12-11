import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Menu {
	private RenderWindow windowMenu = new RenderWindow();
	ArrayList<Images> image = new ArrayList<Images>();
	ArrayList<PlayMusic> music = new ArrayList<PlayMusic>();
	ArrayList<MenuButtons> buttons = new ArrayList<MenuButtons>();
	private Font font = new Font();
	private Text startButton = new Text();
	private Text tutorialButton = new Text();
	private Text controlButton = new Text();
	private Text quitButton = new Text();
	// private Texture startButton = new Texture();
	// private Sprite startButtonImage = new Sprite();
	// private Texture tutorialButton = new Texture();
	// private Sprite tutorialButtonImage = new Sprite();
	// private Texture controlButton = new Texture();
	// private Sprite controlButtonImage = new Sprite();
	// private Texture quitButton = new Texture();
	// private Sprite quitButtonImage = new Sprite();

	public void run() {
		windowMenu.create(new VideoMode(500, 500), "Main Menu");
		music.add(new PlayMusic("src/Sound/background1.wav"));
		music.add(new PlayMusic("src/Sound/Gamestart.wav"));
		music.add(new PlayMusic("src/Sound/buttonpress.wav"));
		image.add(new Images(40f, -120f, 0.6f, 0.6f, "src/Menu/Missing.png"));
		buttons.add(new MenuButtons(190f, 150f, "Start", 50, Color.WHITE));
		buttons.add(new MenuButtons(155f, 225f, "Tutorial", 50, Color.WHITE));
		buttons.add(new MenuButtons(155f, 300f, "Controls", 50, Color.WHITE));
		buttons.add(new MenuButtons(190f, 375f, "Quit", 50, Color.WHITE));
		try {
			font.loadFromFile(Paths.get("src/fontmenu.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		startButton.setFont(font);
		startButton.setString("Start");
		startButton.setColor(Color.WHITE);
		startButton.setCharacterSize(50);
		startButton.setPosition(190f, 150.0f);
		tutorialButton.setFont(font);
		tutorialButton.setString("Tutorial");
		tutorialButton.setColor(Color.WHITE);
		tutorialButton.setCharacterSize(50);
		tutorialButton.setPosition(155f, 225.0f);
		controlButton.setFont(font);
		controlButton.setString("Controls");
		controlButton.setColor(Color.WHITE);
		controlButton.setCharacterSize(50);
		controlButton.setPosition(155f, 300.0f);
		quitButton.setFont(font);
		quitButton.setString("Quit");
		quitButton.setColor(Color.WHITE);
		quitButton.setCharacterSize(50);
		quitButton.setPosition(190f, 375.0f);
		// image.add(new Images(120f, 150f, 1f, 1f, "Menu/Start0.png"));
		// image.add(new Images(120f, 225f, 1f, 1f, "Menu/Start0.png"));
		// image.add(new Images(120f, 300f, 1f, 1f, "Menu/Start0.png"));
		// image.add(new Images(120f, 375f, 1f, 1f, "Menu/Start0.png"));
		// try {
		// startButton.loadFromFile(Paths.get("Menu/Start_After.png"));
		// tutorialButton.loadFromFile(Paths.get("Menu/Start_After.png"));
		// controlButton.loadFromFile(Paths.get("Menu/Start_After.png"));
		// quitButton.loadFromFile(Paths.get("Menu/Start_After.png"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// startButtonImage.setTexture(startButton);
		// startButtonImage.setPosition(125.0f, 160.0f);
		// tutorialButtonImage.setTexture(tutorialButton);
		// tutorialButtonImage.setPosition(125.0f, 235.0f);
		// controlButtonImage.setTexture(controlButton);
		// controlButtonImage.setPosition(125.0f, 310.0f);
		// quitButtonImage.setTexture(quitButton);
		// quitButtonImage.setPosition(125.0f, 385.0f);
		music.get(0).startMusic();

		while (windowMenu.isOpen()) {
			windowMenu.clear();
			for (Images i : image) {
				for (MenuButtons type : buttons) {
					i.draw(windowMenu);
					type.draw(windowMenu);
				}
			}
			if (startButton.getGlobalBounds().contains(Mouse.getPosition(windowMenu).x,
					Mouse.getPosition(windowMenu).y)) {
				windowMenu.clear();
				for (Images i : image) {
					for (MenuButtons type : buttons) {
						i.draw(windowMenu);
						type.draw(windowMenu);
					}
				}
				startButton.setColor(Color.RED);
				windowMenu.draw(startButton);
				startButton.setColor(Color.WHITE);
				if (Mouse.isButtonPressed(Mouse.Button.valueOf("LEFT")) == true) {
					music.get(0).stopMusic();
					music.get(1).startMusic();
					windowMenu.close();
					try {
						Launcher l = new Launcher();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (tutorialButton.getGlobalBounds().contains(Mouse.getPosition(windowMenu).x,
					Mouse.getPosition(windowMenu).y)) {
				windowMenu.clear();
				for (Images i : image) {
					for (MenuButtons type : buttons) {
						i.draw(windowMenu);
						type.draw(windowMenu);
					}
				}
				tutorialButton.setColor(Color.RED);
				windowMenu.draw(tutorialButton);
				tutorialButton.setColor(Color.WHITE);
				if (Mouse.isButtonPressed(Mouse.Button.valueOf("LEFT")) == true) {
					music.get(0).stopMusic();
					music.get(2).startMusic();
					windowMenu.close();
					Tutorial t = new Tutorial();
				}
			}
			if (controlButton.getGlobalBounds().contains(Mouse.getPosition(windowMenu).x,
					Mouse.getPosition(windowMenu).y)) {
				windowMenu.clear();
				for (Images i : image) {
					for (MenuButtons type : buttons) {
						i.draw(windowMenu);
						type.draw(windowMenu);
					}
				}
				controlButton.setColor(Color.RED);
				windowMenu.draw(controlButton);
				controlButton.setColor(Color.WHITE);
				if (Mouse.isButtonPressed(Mouse.Button.valueOf("LEFT")) == true) {
					music.get(0).stopMusic();
					music.get(2).startMusic();
					windowMenu.close();
					Controls a = new Controls();
				}
			}
			if (quitButton.getGlobalBounds().contains(Mouse.getPosition(windowMenu).x,
					Mouse.getPosition(windowMenu).y)) {
				windowMenu.clear();
				for (Images i : image) {
					for (MenuButtons type : buttons) {
						i.draw(windowMenu);
						type.draw(windowMenu);
					}
				}
				quitButton.setColor(Color.RED);
				windowMenu.draw(quitButton);
				quitButton.setColor(Color.WHITE);
				if (Mouse.isButtonPressed(Mouse.Button.valueOf("LEFT")) == true) {
					music.get(2).startMusic();
					windowMenu.close();
					music.get(0).stopMusic();
				}
			}
			for (Event e : windowMenu.pollEvents()) {
				if (e.type == Event.Type.CLOSED) {
					windowMenu.close();
				}
			}
			windowMenu.display();
		}
	}

	public Menu() {
		run();
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
	}
}