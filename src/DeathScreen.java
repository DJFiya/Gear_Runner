import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.*;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import org.w3c.dom.events.MouseEvent;

public class DeathScreen 
{
	private static int cx, cy, mx, my;
	private static Font mWar = null;
	/*
	private String[] messages = {
			"Try using the move buttons", 
			"Don't forget to attack", 
			"Press wasd to use your cannons", 
			"You should probably work on increasing your health", 
			"might be time to ask a friend to help", 
			"we all start from somewhere", 
			"better luck next time",
			"Skill issue?", 
			"Time to repeat the process.",
			"Here we go again...",
			"Try not to do that again next time.",
			"Yikes.",
			"This is embarrassing.",
			"This is the part where you yell at your screen.",
			"Why did you do that?",
			"Mission failed. We'll get 'em next time.",
			"FATALITY!!!",
			"WASTED!!!",
			"OBLITERATED!!!",
			"BRUTALITY!!!",
			"Alt+F4 to close your game. Just a useful reminder.",
			"Time to unplug the router...",
			"Please don't smash your monitor.",
			"FYI, you didn't have to die.",
			"Sigh... do you want me to turn on Baby Mode?",
			"'Tis but a flesh wound.",
			"From zero to hero and then back to zero.",
			"How... unfortunate.",
			"I am inevitable.",
			"Cut!",
			"Respawning...",
			"Just Do It!",
			"Try to avoid the enemies",
			"Is this your first video game?",
			"Keep it up, and you will be almost as skillful as a baby!",
			"I am getting bored watching you die so much...",
			"It might be time to use the cheat code",
			"You're still here?",
			"Please hurry up, I haven't met my family in 10 days",
			"Never gonna give you up, Never gonna let you down",
			"Do you enjoy losing?",
			"Any day now...",
			"I'll be right back",
			"Maybe the goddess shouldn't have picked you",
			"The fate of the world rests on your shoulders... just saying...",
			"Congratulations! You Died!",
			"Maybe this isn't your lane",
			"How much time has it been?",
			"It is never too late to give up",
			"You know the point of the game is to NOT die right?",
			"Try aiming for the enemies"};
			
	private int randInt = 0;
	private int counter = 0;
	private int timer = 5;
	*/
	public DeathScreen()
	{
		try
		{
			mWar = Font.createFont(Font.TRUETYPE_FONT, new File("Mechanoid Warrior.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Mechanoid Warrior.ttf")));
		}catch (IOException | FontFormatException e) {e.printStackTrace();} 
	}
	/*
	public int getRandInt() {return randInt;}
	public String[] getMessages() {return messages;}
	public int getCounter() {return counter;}
	public int getTimer() {return timer;}
	public void reShuffle() 
	{
		randInt = (int)(Math.random()*(getMessages().length));
	}
	public void nextCounter() 
	{
		if(getCounter()==5) counter = 0; else counter++;
	}
	public void setTimer(int s) {timer = s;}
	*/
	public static void setCx(int a){cx = a;}
	public static void setCy(int a){cy = a;}
	public static void setMx(int a){mx = a;}
	public static void setMy(int a){my = a;}
	
	public static void paint(Graphics g)
	{
		Graphics2D d = (Graphics2D) g;
		d.setPaint(new Color(1, 0, 0, 0.5f));
		d.fillRect(0, 0, 1500, 720);
		d.setColor(Color.WHITE);
		d.setFont(mWar);
		d.setFont(g.getFont().deriveFont(Font.PLAIN, 120));
		d.drawString("YOU DIED", 460, 300);
		d.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
		d.setColor(Color.WHITE);
		//d.drawString("The Goddess Gifts You The Power Of Re-Birth", 345, 400);
		d.setColor(Color.CYAN);
		//d.drawString("\"" + getMessages()[getRandInt()]+ "\"", 750-(int)(getMessages()[getRandInt()].length()*10.2), 500);
		if(mx>=670&&mx<=840&&570<=my&&my<=610)d.setColor(new Color(73, 117, 155));
		else d.setColor(Color.LIGHT_GRAY);
		d.fillRect(670, 570, 170 , 40);
		d.setColor(Color.BLACK);
		d.drawRect(670, 570, 170, 40);
		d.setColor(Color.WHITE);
		d.drawString("Respawn",678, 600);
		
		/*
		d.setColor(Color.GRAY);
		d.fillOval(730, 600, 10, 10);
		d.fillOval(760, 600, 10, 10);
		d.fillOval(710, 630, 10, 10);
		d.fillOval(780, 630, 10, 10);
		d.fillOval(730, 660, 10, 10);
		d.fillOval(760, 660, 10, 10);
		d.setColor(Color.WHITE);
		if(getCounter() == 0) d.fillOval(730, 600, 10, 10);
		else if(getCounter() == 1) d.fillOval(760, 600, 10, 10);
		else if(getCounter() == 2) d.fillOval(780, 630, 10, 10);
		else if(getCounter() == 3) d.fillOval(760, 660, 10, 10);
		else if(getCounter() == 4) d.fillOval(730, 660, 10, 10);
		else d.fillOval(710, 630, 10, 10);
		if(getTimer()<=0)
		{
			nextCounter();
			setTimer(5);
		}
		else setTimer(getTimer()-1);
		*/
	}
	
	public static void move()
	{
		if(cx>=670&&cx<=840&&570<=cy&&cy<=610) Interface.respawn();
		cx = 0;
		cy = 0;
	}
}
