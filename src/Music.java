import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineListener;
import javax.swing.JOptionPane;

public class Music 
{
	public static String musicSP = "space_ranger.wav";
	public static String monsterDie = "monster_die3.wav";
	public static String move = "move.wav";
	public static String pickUp = "pick_up_item.wav";
	public static String button = "button.wav";
	static FloatControl fc1, fc2, fc3, fc4, fc5;
	static boolean flag = true;
	public Music(){}
	
	public static void playMusic()
	{
		try
		{
			File MusicPath = new File(musicSP);
			if(MusicPath.exists())
			{
				AudioInputStream audioInput  = AudioSystem.getAudioInputStream(MusicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.loop(clip.LOOP_CONTINUOUSLY);
				clip.start();
				fc1 = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				fc1.setValue(-30.0f);
			}
			else
			{
				System.out.println("Can't find file");
			}
		}catch(Exception e) {e.printStackTrace();;}	
	}
	
	public static void playMonsterDie()
	{
		try
		{
			File MusicPath = new File(monsterDie);
			if(MusicPath.exists())
			{
				AudioInputStream audioInput  = AudioSystem.getAudioInputStream(MusicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				fc2 = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				fc2.setValue(-10.0f);
			}
			else
			{
				System.out.println("Can't find file");
			}
		}catch(Exception e) {e.printStackTrace();;}	
	}
	
	public static void playMoveSound()
	{
		try
		{
			File MusicPath = new File(move);
			if(MusicPath.exists())
			{
				AudioInputStream audioInput  = AudioSystem.getAudioInputStream(MusicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				if(!flag) clip.loop(clip.LOOP_CONTINUOUSLY);
				fc3 = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				fc3.setValue(-10.0f);
			}
			else
			{
				System.out.println("Can't find file");
			}
		}catch(Exception e) {e.printStackTrace();;}	
	}
	
	public static void playPickUpItem()
	{
		try
		{
			File MusicPath = new File(pickUp);
			if(MusicPath.exists())
			{
				AudioInputStream audioInput  = AudioSystem.getAudioInputStream(MusicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				fc4 = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				fc4.setValue(-10.0f);
			}
			else
			{
				System.out.println("Can't find file");
			}
		}catch(Exception e) {e.printStackTrace();;}	
	}
	
	public static void playButton()
	{
		try
		{
			File MusicPath = new File(button);
			if(MusicPath.exists())
			{
				AudioInputStream audioInput  = AudioSystem.getAudioInputStream(MusicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				fc5 = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				fc5.setValue(-10.0f);
			}
			else
			{
				System.out.println("Can't find file");
			}
		}catch(Exception e) {e.printStackTrace();;}	
	}
	
	public static void stopMove()
	{
		flag = false;
	}
	
	public static void startMove()
	{
		flag = true;
	}
}
