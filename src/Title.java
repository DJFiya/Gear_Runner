import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.*;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import org.w3c.dom.events.MouseEvent;


public class Title 
{
	private static BufferedImage background = null;
	private static BufferedImage start0 = null;
	private static BufferedImage start1 = null;
	private static BufferedImage input0 = null;
	private static BufferedImage input1 = null;
	private static BufferedImage story0 = null;
	private static BufferedImage story1 = null;
	private static BufferedImage bg2  = null;
	private static BufferedImage back = null;
	private static BufferedImage back1 = null;
	private static BufferedImage next = null;
	private static BufferedImage next1 = null;
	private static BufferedImage title = null;
	private static Font mWar;
	private static int mx, my, cx, cy;
	public static boolean displayControlMenu = false;
	public static boolean displayStoryMenu = false;
	private static int storyPg = 1;
	public Title()
	{
		try
		{
			title = ImageIO.read(new File("GearRunner.png"));
			background  = ImageIO.read(new File("bg.png"));
			start0 = ImageIO.read(new File("start0.png"));
			start1 = ImageIO.read(new File("start1.png"));
			input0 = ImageIO.read(new File("input0.png"));
			input1 = ImageIO.read(new File("input1.png"));
			story0 = ImageIO.read(new File("story0.png"));
			story1 = ImageIO.read(new File("story1.png"));
			bg2 = ImageIO.read(new File("bg2.png"));
			back = ImageIO.read(new File("Back0.png"));
			back1 = ImageIO.read(new File("Back1.png"));
			next = ImageIO.read(new File("Next0.png"));
			next1 = ImageIO.read(new File("Next1.png"));
			mWar = Font.createFont(Font.TRUETYPE_FONT, new File("Mechanoid Warrior.ttf"));
			GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge2.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Mechanoid Warrior.ttf")));
			Thread.sleep(500);
		} catch (FontFormatException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}	
	}
	
	public static void setCx(int a){cx = a;}
	public static void setCy(int a){cy = a;}
	public static void setMx(int a){mx = a;}
	public static void setMy(int a){my = a;}
	
	public static void paint(Graphics g)
	{
		Graphics2D t = (Graphics2D) g;
		t.drawImage(background, 0, 0, 1500, 720, null);
		t.setPaint(new Color(0, 0, 0, 0.6f));
		t.fillRect(0, 0, 1500, 720);
		t.setFont(mWar);
		t.setFont(g.getFont().deriveFont(Font.PLAIN, 70));
		t.drawImage(title, 322, 10, 856, 112, null);
		t.drawImage(bg2, 570, 130, 360, 480, null);
		if(650<=mx && mx<=850 && 200<=my && my<=300)t.drawImage(start1, 650, 200, 200, 100, null);
		else t.drawImage(start0, 650, 200, 200, 100, null);
		if(650<=mx && mx<=850 && 320<=my && my<=420)t.drawImage(input1, 650, 320, 200, 100, null);
		else t.drawImage(input0, 650, 320, 200, 100, null);
		if(650<=mx && mx<=850 && 440<=my && my<=640)t.drawImage(story1, 650, 440, 200, 100, null);
		else t.drawImage(story0, 650, 440, 200, 100, null);
		if(displayControlMenu) 
		{
			t.fillRect(0, 0, 1500, 720);
			Menu.drawControlMenu(t);
			if(mx>=1150-80 && mx<=1150-40 && my>=640-80 && my<=640-40) t.drawImage(back1, 1150 -80, 640-80, 40, 40, null);
			else t.drawImage(back, 1150 -80, 640-80, 40, 40, null);
		}
		else if(displayStoryMenu)
		{
			t.fillRect(0, 0, 1500, 720);
			if(storyPg==1)Menu.drawStoryMenu1(t);
			else if(storyPg==2) Menu.drawStoryMenu2(t);
			else Menu.drawStoryMenu3(t);
			if(mx>=1150-80 && mx<=1150-40 && my>=640-80 && my<=640-40) t.drawImage(back1, 1150 -80, 640-80, 40, 40, null);
			else t.drawImage(back, 1150 -80, 640-80, 40, 40, null);
			if(storyPg<3)
			{
				if(mx>=1150-80 && mx<=1150-40 && my<=160 && my>=120) t.drawImage(next1, 1070, 120, 40, 40, null);
				else t.drawImage(next, 1070, 120, 40, 40, null);
			}
		}
	}
	
	public void move()
	{
		if(displayControlMenu)
		{
			if(cx>=1150-80 && cx<=1150-40 && cy>=640-80 && cy<=640-40) 
			{
				displayControlMenu = false;
				cx = 0;
				cy = 0;
				Music.playButton();
			}
		}
		else if(displayStoryMenu)
		{
			if(cx>=1150-80 && cx<=1150-40 && cy>=640-80 && cy<=640-40) 
			{
				if(storyPg==1)displayStoryMenu = false;
				else  storyPg--;
				cx = 0;
				cy = 0;
				Music.playButton();
			}
			if(cx>=1150-80 && cx<=1150-40 && cy<=160 && cy>=120)
			{
				if(storyPg<3)storyPg++;
				cx = 0;
				cy = 0;
				Music.playButton();
			}
		}
		else
		{
			if(650<=cx && cx<=850 && 200<=cy && cy<=300) {Interface.title = false; Music.playButton();}
			else if(650<=cx && cx<=850 && 320<=cy && cy<=420) {displayControlMenu = true; Music.playButton();}
			else if(650<=cx && cx<=850 && 440<=cy && cy<=640) {displayStoryMenu = true; Music.playButton();}
			cx = 0;
			cy = 0;
		}
	}
}
