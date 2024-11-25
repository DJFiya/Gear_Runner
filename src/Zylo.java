import java.awt.Rectangle;
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
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.util.*;
import java.awt.image.*;
import java.awt.*;


public class Zylo extends Entity
{
	private int maxSpd, spd, maxHp, hp, maxStr, str, money, coinMulti;
	private boolean vis;
	private BufferedImage[] directions = {null, null, null, null};
	int directionIndex;
	public Zylo(int x, int y, int w, int l, int xa, int ya, boolean vis, int maxSpd, int maxHp, int maxStr) 
    {
		super(x, y, w, l, xa, ya, vis);
		this.maxSpd = spd = maxSpd;
		this.maxHp = hp = maxHp;
		this.maxStr = str = maxStr;
		coinMulti = 2;
		directionIndex = 0;
		money = 0;
		try
		{
			directions[0] = ImageIO.read(new File("ZyloUp.png"));
			directions[1] = ImageIO.read(new File("ZyloRight.png"));
			directions[2] = ImageIO.read(new File("ZyloDown.png"));
			directions[3] = ImageIO.read(new File("ZyloLeft.png"));
		}catch(IOException e) {System.out.println("Error");}
    }
    
    public int getX() {return super.x;}
    public int getY() {return super.y;}
    public int getW() {return super.w;}
    public int getL() {return super.l;}
    public int getXa() {return super.xa;}
    public int getYa() {return super.ya;}
    public boolean getVis() {return super.vis;}
    public int getSpd() {return spd;}
    public int getHp() {return hp;}
    public int getStr() {return str;}
    public int getMaxSpd() {return maxSpd;}
    public int getMaxHp() {return maxHp;}
    public int getMaxStr() {return maxStr;}
    public int getMoney() {return money;}
    public int getCoinMulti(){return coinMulti;}

    public void setX(int a) {super.x = a;}
    public void setY(int a) {super.y = a;}
    public void setW(int a) {super.w = a;}
    public void setL(int a) {super.l = a;}
    public void setXa(int a) {super.xa = a;}
    public void setYa(int a) {super.ya = a;}
    public void setVis(boolean a) {super.vis = a;}
    public void setSpd(int a) {spd = a;}
    public void setHp(int a) {hp = a;}
    public void setStr(int a) {str = a;}
    public void setMaxSpd(int a) {maxSpd = a;}
    public void setMaxHp(int a) {maxHp = a;}
    public void setMaxStr(int a) {maxStr = a;}
    public void setMoney(int a) {money = a;}
    public void addMoney(int a) {setMoney(getMoney()+a);}
    public void setCoinMulti(int a) {coinMulti = a;}

    
    public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {setXa(getSpd()); directionIndex = 1;}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {setXa(-getSpd()); directionIndex = 3;}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {setYa(getSpd()); directionIndex = 2;} 
		if (e.getKeyCode() == KeyEvent.VK_UP) {setYa(-getSpd()); directionIndex = 0;} 
	}
	
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) setXa(0);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)setXa(0);
		if (e.getKeyCode() == KeyEvent.VK_DOWN) setYa(0); 
		if (e.getKeyCode() == KeyEvent.VK_UP) setYa(0); 
	}
    
    public void paint(Graphics g)
    {
    	Graphics2D p = (Graphics2D) g;
    	p.drawImage(directions[directionIndex], getX(), getY(), getW(), getL(), null);
    	setSpd(getMaxSpd());
    	setStr(getMaxStr());
    }
    
    public void move(ArrayList<Platform> plats)
    {
    	//System.out.println(getXa() + " " + getYa());
    	for(Platform pl: plats)
    	{
    		platCollide(pl);
    	}
    	super.setX(getX()+getXa());
    	super.setY(getY()+getYa());
    	//System.out.println(getXa() + " " + getYa());
    }
    
    public void platCollide(Platform pl)
    {
    	if(super.collideX(pl)) super.setXa(pl.getXa());
    	if(super.collideY(pl)) super.setYa(pl.getYa());
    }

	
}
