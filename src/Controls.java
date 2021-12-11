import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Controls {
    private RenderWindow window = new RenderWindow();
    ArrayList<Write> text = new ArrayList<Write>();
    ArrayList<Images> image = new ArrayList<Images>();
    ArrayList<PlayMusic> music = new ArrayList<PlayMusic>();
    private Font font = new Font();
    private Text backButton = new Text();

    public void run() {
        window.create(new VideoMode(600, 550), "Tutorial");
        text.add(new Write(200f, 0f, "controls", 80, Color.WHITE));
        text.add(new Write(20f, 100f, "left arrow", 50, Color.WHITE));
        text.add(new Write(20f, 200f, "right arrow", 50, Color.WHITE));
        text.add(new Write(20f, 300f, "spacebar", 50, Color.WHITE));
        text.add(new Write(20f, 400f, "c", 50, Color.WHITE));
        text.add(new Write(400f, 100f, "move left", 50, Color.WHITE));
        text.add(new Write(400f, 200f, "move right", 50, Color.WHITE));
        text.add(new Write(400f, 300f, "jump", 50, Color.WHITE));
        text.add(new Write(400f, 400f, "slash", 50, Color.WHITE));
        music.add(new PlayMusic("src/Sound/caveAmbience.wav"));
        music.add(new PlayMusic("src/Sound/buttonpress.wav"));
        try {
            font.loadFromFile(Paths.get("src/font.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        backButton.setFont(font);
        backButton.setString("Exit");
        backButton.setColor(Color.WHITE);
        backButton.setCharacterSize(50);
        backButton.setPosition(265.0f, 490.0f);
        music.get(0).startMusic();
        while (window.isOpen()) {
            window.clear();
            for (Write type2 : text) {
                type2.draw(window);
            }
            window.draw(backButton);
            if (backButton.getGlobalBounds().contains(Mouse.getPosition(window).x, Mouse.getPosition(window).y)) {
                window.clear();
                for (Write type2 : text) {
                    type2.draw(window);
                }
                backButton.setColor(Color.RED);
                window.draw(backButton);
                backButton.setColor(Color.WHITE);
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

    public Controls() {
        run();
    }

    public static void main(String[] args) {
        Controls c = new Controls();
    }
}