import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jsfml.audio.*;
import org.jsfml.system.Time;
//import org.jsfml.audio.SoundBuffer;

public class SoundPlay {
	
	//private static SoundBuffer Buffer=new SoundBuffer();
	
	private static Music music=new Music();
	//private static Sound sound=new Sound();
	
	public SoundPlay() {

	}
	
	public void Gamestart() throws IOException{
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/Gamestart.wav"));
		sound.setBuffer(Buffer);
		sound.setLoop(false);
		sound.play();
	}
	
	public void Purchase() throws IOException{
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/Purchase.wav"));
		sound.setBuffer(Buffer);
		sound.setLoop(false);
		sound.play();
	}
	
	public void injured() throws IOException{
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/injured.wav"));
		sound.setBuffer(Buffer);
		sound.setLoop(false);
		sound.play();
	}
	public void injuredEnemy() throws IOException{
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/injured.wav"));
		sound.setBuffer(Buffer);
		sound.setLoop(false);
		sound.setPitch(0.75f);
		sound.play();
	}
	
	public void walking() throws IOException{
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/walking.wav"));
		sound.setBuffer(Buffer);
		sound.setPitch(1.5f);
		sound.setLoop(false);
		sound.play();
	}
	
	public void jump() throws IOException {
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/jump.wav"));
		sound.setBuffer(Buffer);
		sound.setLoop(false);
		sound.play();
	}
	
	public void attack() throws IOException{
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/attack.wav"));
		sound.setBuffer(Buffer);
		sound.setPitch(0.75f);
		sound.setLoop(false);
		sound.play();
	}

	public void shooting() throws IOException {
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/shooting.wav"));
		sound.setBuffer(Buffer);
		sound.setLoop(false);
		sound.play();
	}

	public void cave() throws IOException {
		Sound sound=new Sound();
		SoundBuffer Buffer=new SoundBuffer();
		Buffer.loadFromFile(Paths.get("src/Sound/caveAmbience.wav"));
		sound.setBuffer(Buffer);
		sound.setLoop(true);
		sound.play();
	}
	
	public void background() throws IOException {
		music.openFromFile(Paths.get("src/Sound/background1.wav"));
		System.out.println(music.getDuration());
		music.setLoop(true);
		music.play();
		System.out.println(music.isLoop());
		System.out.println(music.getStatus());
		System.out.println(music.getVolume());
		System.out.println(music.getPitch());
	}
	
}
