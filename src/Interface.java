import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.util.*;
import java.awt.image.*;

@SuppressWarnings("serial")
public class Interface extends JPanel
{
	ArrayList<Platform> plats = new ArrayList<Platform>();
	static Zylo z = new Zylo(750-20, 360-20, 40, 40, 0, 0, true, 2, 10, 5);
	Level l = new Level(this);
	GUI gui = new GUI(this);
	int spawnCounter = 0;
	int shootCounter = 0;
	//static int respawnMaxCounter = 400;
	//static int respawnCounter = 0;
	int diffTimer = 0;
	int score = 0;
	//Enemy e = new Enemy(this, 80, 80, 20, 20, 0, 0, true, 2, 6, 3);
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Item> items = new ArrayList<Item>();
	static DeathScreen ds = new DeathScreen();
	static boolean showDs = false;
	static boolean menu = false;
	static boolean title = true;
	static Menu m = new Menu(z);
	static Music mu = new Music();
	static Title t = new Title();
	
	public PathFinder pF = new PathFinder(this);
	public Interface()
	{
		addKeyListener(new KeyListener() 
		{
			 @Override
			 public void keyTyped(KeyEvent e) {}
			 @Override
			 public void keyReleased(KeyEvent e)
			 {
				 if(!menu)z.keyReleased(e);
			 }
			 @Override
			 public void keyPressed(KeyEvent e)
			 {
				if(!menu&&!title)
				{
					z.keyPressed(e);
					if (e.getKeyCode() == KeyEvent.VK_W) shoot(0, -z.getSpd());
					if (e.getKeyCode() == KeyEvent.VK_A) shoot(-z.getSpd(), 0);
					if (e.getKeyCode() == KeyEvent.VK_S) shoot(0, z.getSpd());
					if (e.getKeyCode() == KeyEvent.VK_D) shoot(z.getSpd(), 0);
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					if(title)
					{
						Title.displayControlMenu = false;
						Title.displayStoryMenu = false;
					}
					else if(!showDs)
					{
						if(menu) {menu = false;}
						else {menu = true; m.setMenPage(0); z.setXa(0); z.setYa(0);}
					}
				}
			 }
			 
		}); 
		addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) 
            {
				if(showDs)
				{
					DeathScreen.setCx(e.getX());
					DeathScreen.setCy(e.getY());
				}
            	if(menu)
				{
                	Menu.setCx(e.getX());
                	Menu.setCy(e.getY());
                	//System.out.println(e.getX() + " " + e.getY());
					//System.out.println(m.getCx());
					//System.out.println(m.getCy());
				}
				if(title)
				{
					Title.setCx(e.getX());
                	Title.setCy(e.getY());
				}
				/*
				if(t.getTitle()){
                	t.setCx(e.getX());
                	t.setCy(e.getY());
					//System.out.println(t.getCx());
					//System.out.println(t.getCy());
				}
				if(vic.getVictory()){
                	vic.setCx(e.getX());
                	vic.setCy(e.getY());
					//System.out.println(vic.getCx());
					//System.out.println(vic.getCy());
				}
				*/
            }
        });
		addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseMoved(MouseEvent e) {
				if(showDs)
				{
					DeathScreen.setMx(e.getX());
					DeathScreen.setMy(e.getY());
				}
            	if(menu)
				{
                	m.setMx(e.getX());
                	m.setMy(e.getY());
                	//System.out.println(e.getX() + " " + e.getY());
				}
				if(title)
				{
					Title.setMx(e.getX());
					Title.setMy(e.getY());
				}
				/*
				if(t.getTitle()){
                	t.setMx(e.getX());
                	t.setMy(e.getY());
				}
				if(vic.getVictory()){
                	vic.setMx(e.getX());
                	vic.setMy(e.getY());
				}
				*/
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
        });
		setFocusable(true);
		requestFocusInWindow();
	}
	public static void main(String[] args) throws InterruptedException 
	{
		JFrame frame = new JFrame("Gear Runner");
		Interface p = new Interface();
		frame.add(p);
		frame.setSize(1516, 759);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Music.playMusic();
		while (true)
		{
			//if(z.getXa()==0 && z.getYa()==0) Music.stopMove();
			//else {Music.startMove();}
			if(title)
			{
				t.move();
			}
			else 
			{
				if(!showDs&&!title&&menu) Menu.move();
				if(!showDs&&!menu) p.move();	
			}		
			p.repaint();
			Thread.sleep(10); 
			//System.out.println(menu);
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g); 
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, 1500, 720);
		l.paint(g2d);
		for(Platform pl: plats)
		{
			if (pl.getVis()) pl.paint(g2d);
		}
		Iterator<Enemy> enems = enemies.iterator();
		while (enems.hasNext()) 
		{ 
			Enemy e = enems.next(); 
			e.paint(g2d);
		}
		for(Projectile pr: projectiles)
		{
			pr.paint(g2d);
		}
		for(Item i: items)
		{
			i.paint(g2d);
		}
		z.paint(g2d);
		gui.paint(g2d);
		if(menu) Menu.paint(g2d);
		if(title) Title.paint(g2d);
		if(showDs) 
		{
			DeathScreen.paint(g2d);
			DeathScreen.move();
			//respawnCounter--;
			//if(respawnCounter==0) showDs = false;
		}
	}
	
	public void move()
	{
		if(diffTimer<Integer.MAX_VALUE)diffTimer++;
		if(z.getHp()<1) plDies();
		//System.out.println(z.getHp());
		int calc = 200-diffTimer/300;
		if(spawnCounter<=0) {spawn(); if(calc>30)spawnCounter = calc; else spawnCounter = 30; System.out.println(spawnCounter);}
		else spawnCounter--;
		for(Platform pl: plats)
		{
			if (pl.getVis()) pl.move();
		}
//		System.out.println(pl.getX());
		z.move(plats);
		Iterator<Enemy> enems = enemies.iterator();
		while (enems.hasNext()) 
		{ 
			Enemy e = enems.next(); 
			e.move();
		}
		for(Projectile pr: projectiles)
		{
			pr.move();
		}
		Iterator<Enemy> enems1 = enemies.iterator();
		while (enems1.hasNext()) 
		{ 
			Enemy e = enems1.next(); 
			if (e.getHp()<1||(e.collide(z)&&e.getCounter()<1))
			{
				if(e.collide(z) && e.getCounter()<1) z.setHp(z.getHp()-e.getStr());
				if(e.getHp()<1) 
				{
					int randInt = (int)(Math.random()*10);
					if(randInt>0)items.add(new Item(e.getX()-5, e.getY()-5, 30, 30, true, (e.getType()+1)*z.getCoinMulti(), "coin"));
					else items.add(new Item(e.getX()-5, e.getY()-5, 30, 30, true, (e.getType()+1)*2, "gear"));
				}
				if(e.getHp()<1||e.getCounter()<1)enems1.remove();
				Music.playMonsterDie();
			}
		}
		Iterator<Item> ites = items.iterator();
		while (ites.hasNext()) 
		{ 
			Item i = ites.next(); 
			if (i.collide(z)) 
			{
				if(i.getType()=="coin") z.addMoney(i.getVal());
				else if(i.getType()=="gear")
				{
					if(z.getHp()+i.getVal()>=z.getMaxHp()) z.setHp(z.getMaxHp());
					else z.setHp(z.getHp()+i.getVal());
				}
				Music.playPickUpItem();
				ites.remove();   
			}
		}
		CopyOnWriteArrayList<Projectile> projIt = new CopyOnWriteArrayList<Projectile>(projectiles	);
		Iterator<Projectile> proj = projIt.iterator();
		List<Projectile> projRemove = new LinkedList<Projectile>();
		while (proj.hasNext()) 
		{
		    if(proj.hasNext()) 
		    {
		    	Projectile pr = proj.next();
			    boolean alive = true;
			    for (Platform plat : plats) {
			        if (pr.pCollided(plat)) {
			            projRemove.add(pr);  // Use iterator's remove method
			            alive = false;
			            break;
			        }
			    }
			    if (alive) {
			        for (Enemy e : enemies) {
			        	if(e.getHp()<=0) continue;
			        	if (pr.eCollided(e)) {
			                if (e.getCounter() < 1) e.setHp(e.getHp() - pr.getDmg());
			                else e.setHp(0);
				            projRemove.add(pr);  // Use iterator's remove method
			                alive = false;
			                break;
			            }
			        }
			    }
		    }
		    else continue;
		}
		projectiles.removeAll(projRemove);
		projRemove.clear();
		
		if(shootCounter>0) shootCounter--;
	}
	
	public void spawn()
	{
		int loc = (int)(Math.random()*4);
		int diff = (int)(Math.random()*diffTimer);
		int spdM = 1, hpM = 1, strM = 1, type = 0;
		
		if(diff>19000)
		{
			spdM = (int)(Math.random()*3 +2);
			hpM = (int)(Math.random()*3 +10);
			strM = (int)(Math.random()*3 +8);
			type = 7;
		}
		else if(diff>14000)
		{
			spdM = (int)(Math.random()*3 +2);
			hpM = (int)(Math.random()*3 +6);
			strM = 1;
			type = 6;
		}
		else if(diff>10000)
		{
			spdM = (int)(Math.random()*3 +2);
			hpM = 1;
			strM = (int)(Math.random()*3 +6);
			type = 5;
		}
		else if(diff>7000)
		{
			spdM = 1;
			hpM = (int)(Math.random()*3 +6);
			strM = (int)(Math.random()*3 +6);
			type = 4;
		}
		else if(diff>4000)
		{
			spdM = (int)(Math.random()*3 +2);
			hpM = 1;
			strM = 1;
			type = 3;
		}
		else if(diff>2000)
		{
			spdM = 1;
			hpM = (int)(Math.random()*3 +2);
			strM = 1;
			type = 2;
		}
		else if(diff>1000)
		{
			spdM = 1;
			hpM = 1;
			strM = (int)(Math.random()*3 +2);
			type = 1;
		}
		else
		{
			spdM = hpM = strM = 1;
			type = 0;
		}
			
		if(loc == 0) 
		{
			int randNum = (int)(Math.random()*1320)+80;
			enemies.add(new Enemy(this, randNum, 80, 20, 20, 0, 0, true, spdM, hpM, strM, type));
		}
		else if(loc == 1) 
		{
			int randNum = (int)(Math.random()*540)+80;
			enemies.add(new Enemy(this, 80, randNum, 20, 20, 0, 0, true, spdM, hpM, strM, type));
		}
		else if(loc == 2) 
		{
			int randNum = (int)(Math.random()*1320)+80;
			enemies.add(new Enemy(this, randNum, 620, 20, 20, 0, 0, true, spdM, hpM, strM, type));
		}
		else 
		{
			int randNum = (int)(Math.random()*540)+80;
			enemies.add(new Enemy(this, 1400, randNum, 20, 20, 0, 0, true, spdM, hpM, strM, type));
		}
	}
	
	public void shoot(int xa, int ya)
	{
		if(shootCounter<=0) 
		{
			if(xa==0)projectiles.add(new Projectile(z.getX()+15, z.getY()+15, 10, 10, 0, ya+2*Math.abs(ya)/ya, true, z.getStr()));
			else projectiles.add(new Projectile(z.getX()+15, z.getY()+15, 10, 10, xa+2*Math.abs(xa)/xa, 0, true, z.getStr()));
			shootCounter = 30;
		}
	}
	
	public void plDies()
	{
		showDs = true;
		//ds.reShuffle();
		enemies.clear();
		projectiles.clear();
		items.clear();
		diffTimer-=200;
		/* Re-enable the following code once you figure out how to adjust pathfinder for new map
		ui.plats.clear();
		l = new Level(this);
		pF.instantiateNodes();
		pF = new PathFinder(this);
		*/
	}
	
	public static void respawn()
	{
		z.setHp(z.getMaxHp());
		z.setX(730);
		z.setY(340);
		z.setMoney(0);
		showDs = false;
	}
}