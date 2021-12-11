import org.jsfml.audio.*;
import java.io.IOException;
import java.nio.file.Paths;

public class PlayMusic {
    SoundBuffer soundBuffer = new SoundBuffer();
    Sound sound = new Sound();

    public PlayMusic(String file) {
        try {
            soundBuffer.loadFromFile(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sound.setBuffer(soundBuffer);
    }

    public void startMusic() {
        sound.play();
    }

    public void stopMusic() {
        sound.stop();
    }
}