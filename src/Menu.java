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

public class Menu 
{
	protected static final int x = 350, y = 80, w = 800, l = 560;
	private static int cx, cy, mx, my;
	private int hol;
	private int count;
	private String menType, input;
	private static BufferedImage blankMenu = null;
	private static int menPage;
	private static BufferedImage openMenu = null;
	private static BufferedImage back = null;
	private static BufferedImage back1 = null;
	private static BufferedImage plus = null;
	private static BufferedImage plus1 = null;
	private static BufferedImage coin = null;
	private static BufferedImage heart = null;
	private static BufferedImage speed = null;
	private static BufferedImage cb = null;
	private static BufferedImage[][] items = new BufferedImage[5][5];
	private static BufferedImage lock = null;
	private static BufferedImage next = null;
	private static BufferedImage next1 = null;
	private static Font pix;
	private static Font mWar;
	private static Zylo z;
	private static int[] priceScaling = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000, 20000000, 50000000, 100000000, Integer.MAX_VALUE};
	private static double[] incValues = {5, 1, 5, 1.5};
	private static int[] timesPurchased = {0, 0, 0, 0};
	private static int storyPg = 1;
	
	
	public static int getX() {return x;}
	public static int getY() {return y;}
	public static int getW() {return w;}
	public static int getL() {return l;}
	
	public Menu(Zylo z)
	{
		this.z = z;
		try
		{
			blankMenu  = ImageIO.read(new File("BlankMenu.png"));
			openMenu  = ImageIO.read(new File("OpenMenu.png"));
			back = ImageIO.read(new File("Back0.png"));
			back1 = ImageIO.read(new File("Back1.png"));
			next = ImageIO.read(new File("Next0.png"));
			next1 = ImageIO.read(new File("Next1.png"));
			plus = ImageIO.read(new File("Plus0.png"));
			plus1 = ImageIO.read(new File("Plus1.png"));
			coin = ImageIO.read(new File("Coin.png"));
			heart = ImageIO.read(new File("Life.png"));
			speed = ImageIO.read(new File("Speed.png"));
			cb = ImageIO.read(new File("CannonBall.png"));
			lock = ImageIO.read(new File("Lock.png"));
			pix = Font.createFont(Font.TRUETYPE_FONT, new File("pixelated.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("pixelated.ttf")));
			Thread.sleep(500);
			mWar = Font.createFont(Font.TRUETYPE_FONT, new File("Mechanoid Warrior.ttf"));
			GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge2.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Mechanoid Warrior.ttf")));
			Thread.sleep(500);
		} catch (FontFormatException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}	
	}
	
	public static int getCx(){return cx;}
	public static int getCy(){return cy;}
	public static int getMx(){return mx;}
	public static int getMy(){return my;}
	public static int getMenPage(){return menPage;}
	public int getCount(){return count;}
	public String getMenType(){return menType;}
	public static int getStoryPg() {return storyPg;}
	
	public static void setCx(int a){cx = a;}
	public static void setCy(int a){cy = a;}
	public void setMx(int a){mx = a;}
	public void setMy(int a){my = a;}
	public void setMenPage(int a){menPage = a;}
	public void setCount(){count = 0;}
	public void setMenType(String a){menType = a;}
	public void setInput(String a){input = a;}
	public static void setStoryPg(int a) {storyPg = a;}
	
	public static void paint(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		p.setFont(mWar);
		p.setFont(g.getFont().deriveFont(Font.PLAIN, 70));
		Color color = new Color(0, 0, 0, (float)0.9);
		p.setPaint(color);
		p.fillRect(0, 0, 1500, 720);
		if(getMenPage()==0) 
		{
			drawMainMenu(p);
		}
		else if(getMenPage()==1)
		{
			drawGarageMenu(p);
		}
		else if(getMenPage()==2)
		{
			drawControlMenu(p);
		}
		else if(getMenPage()==3)
		{
			if(getStoryPg()==1) drawStoryMenu1(p);
			else if(getStoryPg()==2) drawStoryMenu2(p);
			else drawStoryMenu3(p);
		}
		else p.drawImage(blankMenu, getX(), getY(), getW(), getL(), null);
		for(int i = 0; i < 5; i++)
		{
			color = new Color(0, 0, 0, (float)(i*2/10.0)); 
			p.setPaint(color);
			p.fillRect(350, 615+i*5, 800, 5);
			p.fillRect(350, 100-i*5, 800, 5);
			p.fillRect(1125+i*5, 80, 5, 560);
			p.fillRect(370-i*5, 80, 5, 560);
		}
		if(getMenPage()!=0)
		{
			if(mx>=1150-80 && mx<=1150-40 && my>=640-80 && my<=640-40) p.drawImage(back1, 1150 -80, 640-80, 40, 40, null);
			else p.drawImage(back, 1150 -80, 640-80, 40, 40, null);
		}
		if(getMenPage()==3&&getStoryPg()<3)
		{
			if(mx>=1150-80 && mx<=1150-40 && my<=160 && my>=120) p.drawImage(next1, 1070, 120, 40, 40, null);
			else p.drawImage(next, 1070, 120, 40, 40, null);
		}
	}
	
	public static void drawMainMenu(Graphics g2d)
	{
		Graphics2D p = (Graphics2D) g2d;
		p.drawImage(openMenu, getX(), getY(), getW(), getL(), null);
		Color color = new Color(0, (float) 0.5, (float) 0.8, (float)0.8);
		Color color1 = new Color((float)1, (float)1, 0, (float)1);
		Color color2 = new Color((float)1, (float)0.5, 0, (float)1);
		Color startColor = color1;
		Color endColor = color2;
		GradientPaint gradientPaint = new GradientPaint(595, 200, startColor, 595, 350, endColor);
		p.setPaint(gradientPaint);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 70));

		p.drawString("GEAR", 650, 240);
		p.drawString("RUNNER", 598, 290);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 30));
		
		p.setPaint(color);
		//p.setFont(pix);
		if(getMy()>315&&getMy()<368&&getMx()>518&&getMx()<722) p.setPaint(color1);
		p.drawString("GARAGE", 555, 348);
		
		p.setPaint(color);
		if(getMy()>315&&getMy()<368&&getMx()>778&&getMx()<982) p.setPaint(color1);

		p.drawString("BUTTONS", 805, 348);
		
		p.setPaint(color);
		if(getMy()>395&&getMy()<448&&getMx()>518&&getMx()<722) p.setPaint(color1);
		p.drawString("STORY", 565, 428);
		
		p.setPaint(color);
		if(getMy()>395&&getMy()<448&&getMx()>778&&getMx()<982) p.setPaint(color1);
		p.drawString("CREDITS", 812, 428);
		
		p.setPaint(color);
		if(getMy()>475&&getMy()<528&&getMx()>518&&getMx()<722) p.setPaint(color1);
		p.drawString("UNPAUSE", 542, 508);
		
		p.setPaint(color);
		if(getMy()>475&&getMy()<528&&getMx()>778&&getMx()<982) p.setPaint(color1);
		p.drawString("QUIT", 845, 508);
	}
	
	public static void drawControlMenu(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		p.drawImage(blankMenu, getX(), getY(), getW(), getL(), null);
		Color color = new Color(0, (float) 0.5, (float) 0.8, (float)0.8);
		Color color1 = new Color((float)1, (float)1, 0, (float)1);
		Color color2 = new Color((float)1, (float)0.5, 0, (float)1);
		Color startColor = color1;
		Color endColor = color2;
		GradientPaint gradientPaint = new GradientPaint(595, 200, startColor, 595, 280, endColor);
		p.setPaint(gradientPaint);
		p.setFont(mWar);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 70));
		p.drawString("Controls", 550, 240);	
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 20));
		gradientPaint = new GradientPaint(470, 250, color, 470, 800, Color.CYAN);
		p.setPaint(gradientPaint);
		
		p.drawString("Movement:", 470, 280);
		p.drawString("[UP ARROW] Move Up", 470, 320);
		p.drawString("[LEFT ARROW] Move Left", 470, 340);
		p.drawString("[DOWN ARROW] Move Down", 470, 360);
		p.drawString("[RIGHT ARROW] Move Right", 470, 380);
		
		p.drawString("Attack:", 810, 280);
		p.drawString("[W] Shoot Up", 810, 320);
		p.drawString("[A] Shoot Left", 810, 340);
		p.drawString("[S] Shoot Down", 810, 360);
		p.drawString("[D] Shoot Right", 810, 380);
		
		p.drawString("Use Item:", 470, 420);
		p.drawString("[1] Use Item Slot 1", 470, 440);
		p.drawString("[2] Use Item Slot 2", 470, 460);
		p.drawString("[3] Use Item Slot 3", 470, 480);
		p.drawString("[4] Use Item Slot 4", 470, 500);
		
		p.drawString("Other:", 810, 420);
		p.drawString("[Esc] Leave Menu", 810, 440);
	}
	
	public static void drawStoryMenu1(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		p.drawImage(blankMenu, getX(), getY(), getW(), getL(), null);
		Color color = new Color(0, (float) 0.5, (float) 0.8, (float)0.8);
		Color color1 = new Color((float)1, (float)1, 0, (float)1);
		Color color2 = new Color((float)1, (float)0.5, 0, (float)1);
		Color startColor = color1;
		Color endColor = color2;
		GradientPaint gradientPaint = new GradientPaint(500, 200, startColor, 500, 280, endColor);
		p.setPaint(gradientPaint);
		p.setFont(mWar);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 70));
		p.drawString("Story P1", 575, 240);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 15));
		p.setColor(Color.CYAN.darker());
		p.drawString("Years ago, humanity was renowed for their innovation.", 465, 280);
		p.drawString("However, innovation led scientiest down a dark path.", 465, 300);
		p.drawString("Dreams of AI robot pets turned into the cybernetic war.", 465, 320);
		p.setColor(Color.green.darker());
		p.drawString("These AI's multiplied leaving nanotech spores in the air.", 465, 380);
		p.drawString("Billions of humans died, but few managed to survive.", 465, 400);
		p.drawString("Only the toughest, the smartest, and the strongest lived...", 465, 420);
		p.setColor(color.yellow.darker().darker());
		p.drawString("Well... the toughest, smartest, strongest and Samson.", 465, 480);
		p.drawString("Samson has been protected by one machine, dedicated to him.", 465, 500);
		p.drawString("What machine you ask? well you!", 465, 520);
	}
	
	public static void drawStoryMenu2(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		p.drawImage(blankMenu, getX(), getY(), getW(), getL(), null);
		Color color = new Color(0, (float) 0.5, (float) 0.8, (float)0.8);
		Color color1 = new Color((float)1, (float)1, 0, (float)1);
		Color color2 = new Color((float)1, (float)0.5, 0, (float)1);
		Color startColor = color1;
		Color endColor = color2;
		GradientPaint gradientPaint = new GradientPaint(500, 200, startColor, 500, 280, endColor);
		p.setPaint(gradientPaint);
		p.setFont(mWar);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 70));
		p.drawString("Story P2", 565, 240);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 15));
		p.setColor(Color.CYAN.darker());
		p.drawString("Samson's brother built this machine years ago.", 465, 280);
		p.drawString("As an engineer, he was worried about the risk.", 465, 300);
		p.drawString("His only goal? Keep Samson safe.", 465, 320);
		p.setColor(Color.green.darker());
		p.drawString("It has been 2 months since the apocalypse started.", 465, 380);
		p.drawString("6 months since his brother mysteriously vanished.", 465, 400);
		p.drawString("All that is left of his brother is this very machine.", 465, 420);
		p.setColor(color.yellow.darker().darker());
		p.drawString("Samson had stayed safe in a bunker since the start.", 465, 480);
		p.drawString("But food is running short, and he is desparate.", 465, 500);
		p.drawString("Now it is time to see what the outside world brings.", 465, 520);
	}
	
	public static void drawStoryMenu3(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		p.drawImage(blankMenu, getX(), getY(), getW(), getL(), null);
		Color color = new Color(0, (float) 0.5, (float) 0.8, (float)0.8);
		Color color1 = new Color((float)1, (float)1, 0, (float)1);
		Color color2 = new Color((float)1, (float)0.5, 0, (float)1);
		Color startColor = color1;
		Color endColor = color2;
		GradientPaint gradientPaint = new GradientPaint(500, 200, startColor, 500, 280, endColor);
		p.setPaint(gradientPaint);
		p.setFont(mWar);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 70));
		p.drawString("Story P3", 565, 240);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 15));
		p.setColor(Color.CYAN.darker());
		p.drawString("You have been developed with basic weaponary.", 465, 280);
		p.drawString("However, the machine is connected to a weird signal.", 465, 300);
		p.drawString("Whoever is on the other end, will trade parts for gold.", 465, 320);
		p.setColor(Color.green.darker());
		p.drawString("This will allow you to upgrade and buy useful items.", 465, 380);
		p.drawString("Once implemented, you can store information for 4 items.", 465, 400);
		p.drawString("These items will help you ensure Samson is safe.", 465, 420);
		p.setColor(color.yellow.darker().darker());
		p.drawString("To help Samson, you must remove the local robots.", 465, 480);
		p.drawString("Your only purpose?.", 465, 500);
		p.drawString("To get rid of these monsters, or die trying.", 465, 520);
	}
	
	public static void drawGarageMenu(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		p.drawImage(blankMenu, getX(), getY(), getW(), getL(), null);
		Color color = new Color(0, (float) 0.5, (float) 0.8, (float)0.8);
		Color color1 = new Color((float)1, (float)1, 0, (float)1);
		Color color2 = new Color((float)1, (float)0.5, 0, (float)1);
		Color startColor = color1;
		Color endColor = color2;
		GradientPaint gradientPaint = new GradientPaint(500, 200, startColor, 500, 280, endColor);
		p.setPaint(gradientPaint);
		p.setFont(mWar);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 70));
		p.drawString("Garage", 595, 240);
		p.drawImage(coin, 1070, 120, 40, 40, null);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 40));
		GradientPaint gp = new GradientPaint(500, 125, startColor, 500, 160, endColor);
		p.setPaint(gp);
		String moneys = z.getMoney()+"";
		p.drawString(moneys, 1070-moneys.length()*30+count1(moneys)*20, 155);
		
		if(mx>=470&&mx<=500&&my>=260&&my<=290) 
		{
			p.drawImage(plus1, 470, 260, 30, 30, null);
			p.setColor(Color.green.darker());
			p.setFont(p.getFont().deriveFont(Font.PLAIN, 10));
			if(priceScaling[timesPurchased[0]]!=Integer.MAX_VALUE) p.drawString("Cost: " + priceScaling[timesPurchased[0]],470, 250);
			else p.drawString("MAXIMUM", 470, 250);
		}
		else p.drawImage(plus, 470, 260, 30, 30, null);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 40));
		p.drawImage(heart, 510, 255, 40, 40, null);
		p.setPaint(Color.GRAY.darker());
		p.drawString(" X"+ z.getMaxHp(), 540, 290);
		
		if(mx>=470&&mx<=500&&my>=340&&my<=370) 
		{
			p.drawImage(plus1, 470, 340, 30, 30, null);
			p.setColor(Color.green.darker());
			p.setFont(p.getFont().deriveFont(Font.PLAIN, 10));
			if(priceScaling[timesPurchased[1]]!=Integer.MAX_VALUE) p.drawString("Cost: " + priceScaling[timesPurchased[1]],470, 330);
			else p.drawString("MAXIMUM", 470, 330);
		}
		else p.drawImage(plus, 470, 340, 30, 30, null);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 40));
		p.drawImage(speed, 510, 335, 40, 40, null);
		p.setPaint(Color.GRAY.darker());
		p.drawString(" X"+ z.getMaxSpd(), 540, 370);
		
		if(mx>=470&&mx<=500&&my>=420&&my<=450) 
		{
			p.drawImage(plus1, 470, 420, 30, 30, null);
			p.setColor(Color.green.darker());
			p.setFont(p.getFont().deriveFont(Font.PLAIN, 10));
			if(priceScaling[timesPurchased[2]]!=Integer.MAX_VALUE) p.drawString("Cost: " + priceScaling[timesPurchased[2]],470, 410);
			else p.drawString("MAXIMUM", 470, 410);
		}
		else p.drawImage(plus, 470, 420, 30, 30, null);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 40));
		p.drawImage(cb, 510, 415, 40, 40, null);
		p.setPaint(Color.GRAY.darker());
		p.drawString(" X"+ z.getMaxStr(), 540, 450);
		
		if(mx>=470&&mx<=500&&my>=500&&my<=530) 
		{
			p.drawImage(plus1, 470, 500, 30, 30, null);
			p.setColor(Color.green.darker());
			p.setFont(p.getFont().deriveFont(Font.PLAIN, 10));
			if(priceScaling[timesPurchased[3]]!=Integer.MAX_VALUE) p.drawString("Cost: " + priceScaling[timesPurchased[3]],470, 490);
			else p.drawString("MAXIMUM", 470, 490);
		}
		else p.drawImage(plus, 470, 500, 30, 30, null);
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 40));
		p.drawImage(coin, 510, 495, 40, 40, null);
		p.setPaint(Color.GRAY.darker());
		p.drawString(" X"+ z.getCoinMulti(), 540, 530);
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j<5; j++)
			{
				drawBox(i, j, p);
			}
		}
	}
	
	public static int count1(String str)
	{
		if(str.length()==0) return 0;
		if(str.charAt(0)=='1') return 1+count1(str.substring(1));
		return 0 + count1(str.substring(1));
	}
	
	public static void move()
	{
		if(getMenPage()==0)
		{
			if(cy>315&&cy<368&&cx>518&&cx<722) //Garage
			{
				menPage = 1;
				cx = 0;
				cy = 0;
				Music.playButton();
			}
			if(cy>315&&cy<368&&cx>778&&cx<982) //Buttons
			{
				menPage = 2;
				cx = 0;
				cy = 0;
				Music.playButton();
			}
			if(cy>395&&cy<448&&cx>518&&cx<722) //Story
			{
				menPage = 3;
				cx = 0;
				cy = 0;
				Music.playButton();
			}
			if(cy>395&&cy<448&&cx>778&&cx<982) {} //Credits
			if(cy>475&&cy<528&&cx>518&&cx<722) //Unpause
			{
				System.out.println(cx + " " + cy);
				System.out.println("Before unpause: " + Interface.menu);
				Interface.menu = false;
				System.out.println("After unpause: " + Interface.menu);
				cx = 0;
				cy = 0;
				Music.playButton();
			}
			if(cy>475&&cy<528&&cx>778&&cx<982) //Quit
			{
				Music.playButton();
				System.exit(0);
			}
		}
		if(getMenPage()!=0)
		{
			if(cx>=1150-80 && cx<=1150-40 && cy>=640-80 && cy<=640-40) 
			{
				if(getMenPage()!=3||getStoryPg()==1)menPage = 0;
				else if(getStoryPg()>1) setStoryPg(getStoryPg()-1);
				cx = 0;
				cy = 0;
				Music.playButton();
			}
		}
		if(getMenPage()==1)
		{
			if(cx>=470&&cx<=500&&cy>=260&&cy<=290)
			{
				if(priceScaling[timesPurchased[0]]!=Integer.MAX_VALUE) purchaseUpgrade(0);
				cx = 0;
				cy = 0;
			}
			if(cx>=470&&cx<=500&&cy>=340&&cy<=370)
			{
				if(priceScaling[timesPurchased[1]]!=Integer.MAX_VALUE) purchaseUpgrade(1);
				cx = 0;
				cy = 0;
			}
			if(cx>=470&&cx<=500&&cy>=420&&cy<=450)
			{
				if(priceScaling[timesPurchased[2]]!=Integer.MAX_VALUE) purchaseUpgrade(2);
				cx = 0;
				cy = 0;
			}
			if(cx>=470&&cx<=500&&cy>=500&&cy<=530)
			{
				if(priceScaling[timesPurchased[3]]!=Integer.MAX_VALUE) purchaseUpgrade(3);
				cx = 0;
				cy = 0;
			}
		}
		if(getMenPage()==3)
		{
			if(getStoryPg()<3)
			{
				if(cx>=1150-80 && cx<=1150-40 && cy<=160 && cy>=120)
				{
					setStoryPg(getStoryPg()+1);
					cx = 0;
					cy = 0;
					Music.playButton();
				}
			}
		}
	}
	
	public static void drawBox(int x, int y, Graphics g)
	{
		Graphics2D b= (Graphics2D) g;
		b.setColor(Color.DARK_GRAY);
		b.fillRect(x*58+730, y*58+250, 50, 50);
		if(items[x][y]==null) b.drawImage(lock,x*58+730, y*58+250, 50, 50, null);
	}
	
	public static void purchaseUpgrade(int type)
	{
		if(isPurchasable(type))
		{
			Music.playButton();
			if(type == 0)
			{
				z.setMaxHp(z.getMaxHp()+(int)incValues[type]);
				z.setHp(z.getHp()+(int)incValues[type]);
				z.setMoney(z.getMoney()-priceScaling[timesPurchased[type]]);
				timesPurchased[type]++;
			}
			else if(type == 1)
			{
				z.setMaxSpd(z.getMaxSpd()+(int)incValues[type]);
				z.setMoney(z.getMoney()-priceScaling[timesPurchased[type]]);
				timesPurchased[type]++;
			}
			else if(type == 2)
			{
				z.setMaxStr(z.getMaxStr()+(int)incValues[type]);
				z.setMoney(z.getMoney()-priceScaling[timesPurchased[type]]);
				timesPurchased[type]++;
			}
			else
			{
				z.setCoinMulti((int)(z.getCoinMulti()*incValues[type]));
				z.setMoney(z.getMoney()-priceScaling[timesPurchased[type]]);
				timesPurchased[type]++;
			}
		}
	}
	
	public static boolean isPurchasable(int type)
	{
		return(z.getMoney()>=priceScaling[timesPurchased[type]]);
	}
	
	//Font Conversions: size 70 --> x = 35, y = 50
}
